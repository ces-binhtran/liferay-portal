/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.file.install.internal.properties;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import java.net.URL;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.framework.BundleContext;

/**
 * @author Matthew Tambara
 */
public class Properties extends AbstractMap<String, String> {

	public static final String DEFAULT_ENCODING = "ISO-8859-1";

	public static boolean contains(char[] array, char valueToFind) {
		if (array == null) {
			return false;
		}

		for (char c : array) {
			if (valueToFind == c) {
				return true;
			}
		}

		return false;
	}

	public Properties() {
	}

	public Properties(boolean substitute) {
		_substitute = substitute;
	}

	public Properties(File location) throws IOException {
		this(location, (InterpolationUtil.SubstitutionCallback)null);
	}

	public Properties(File location, boolean substitute) {
		_location = location;
		_substitute = substitute;
	}

	public Properties(File location, BundleContext context) throws IOException {
		this(
			location,
			new InterpolationUtil.BundleContextSubstitutionCallback(context));
	}

	public Properties(
			File location, InterpolationUtil.SubstitutionCallback callback)
		throws IOException {

		_location = location;
		_callback = callback;

		if (location.exists()) {
			load(location);
		}
	}

	@Override
	public void clear() {
		for (Layout layout : _layoutMap.values()) {
			layout.clearValue();
		}

		_storage.clear();
	}

	@Override
	public Set<Entry<String, String>> entrySet() {
		return new AbstractSet<Entry<String, String>>() {

			@Override
			public Iterator<Entry<String, String>> iterator() {
				return new KeyIterator();
			}

			@Override
			public int size() {
				return _storage.size();
			}

		};
	}

	public List<String> getComments(String key) {
		Layout layout = _layoutMap.get(key);

		if (layout != null) {
			List<String> commentLines = layout.getCommentLines();

			if (commentLines != null) {
				return new ArrayList<>(commentLines);
			}
		}

		return new ArrayList<>();
	}

	public List<String> getFooter() {
		return _footer;
	}

	public List<String> getHeader() {
		return _header;
	}

	public String getProperty(String key) {
		return get(key);
	}

	public String getProperty(String key, String defaultValue) {
		if (get(key) != null) {
			return get(key);
		}

		return defaultValue;
	}

	public List<String> getRaw(String key) {
		Layout layout = _layoutMap.get(key);

		if (layout != null) {
			List<String> valueLines = layout.getValueLines();

			if (valueLines != null) {
				return new ArrayList<>(valueLines);
			}
		}

		List<String> result = new ArrayList<>();

		if (_storage.containsKey(key)) {
			result.add(_storage.get(key));
		}

		return result;
	}

	public boolean isTyped() {
		return _typed;
	}

	public void load(File location) throws IOException {
		try (InputStream inputStream = new FileInputStream(location)) {
			load(inputStream);
		}
	}

	public void load(InputStream inputStream) throws IOException {
		load(new InputStreamReader(inputStream, DEFAULT_ENCODING));
	}

	public void load(Reader reader) throws IOException {
		loadLayout(reader, false);
	}

	public void load(URL url) throws IOException {
		try (InputStream inputStream = url.openStream()) {
			load(inputStream);
		}
	}

	public void loadLayout(Reader reader, boolean maybeTyped)
		throws IOException {

		PropertiesReader propertiesReader = new PropertiesReader(
			reader, maybeTyped);

		boolean hasProperty = false;

		while (propertiesReader.nextProperty()) {
			hasProperty = true;

			_storage.put(
				propertiesReader.getPropertyName(),
				propertiesReader.getPropertyValue());

			int index = _checkHeaderComment(propertiesReader.getCommentLines());

			List<String> commentLines = propertiesReader.getCommentLines();

			int size = commentLines.size();

			if (index < commentLines.size()) {
				commentLines = commentLines.subList(index, size);
			}
			else {
				commentLines = null;
			}

			_layoutMap.put(
				propertiesReader.getPropertyName(),
				new Layout(
					commentLines,
					new ArrayList<>(propertiesReader.getValueLines())));
		}

		Boolean typed = propertiesReader.isTyped();

		if (!maybeTyped || (typed == null) || !typed) {
			_typed = false;

			for (Entry<String, String> entry : _storage.entrySet()) {
				entry.setValue(_unescapeJava(entry.getValue()));
			}
		}
		else {
			_typed = true;
		}

		if (hasProperty) {
			_footer = new ArrayList<>(propertiesReader.getCommentLines());
		}
		else {
			_header = new ArrayList<>(propertiesReader.getCommentLines());
		}

		if (_substitute) {
			substitute();
		}
	}

	public Enumeration<?> propertyNames() {
		return Collections.enumeration(_storage.keySet());
	}

	public String put(
		String key, List<String> commentLines, List<String> valueLines) {

		commentLines = new ArrayList<>(commentLines);

		valueLines = new ArrayList<>(valueLines);

		String escapedKey = _escapeKey(key);

		StringBuilder sb = new StringBuilder();

		if (valueLines.isEmpty()) {
			valueLines.add(escapedKey + "=");

			sb.append(escapedKey);
			sb.append("=");
		}
		else {
			String value = valueLines.get(0);

			String realValue = value;

			if (!_typed) {
				realValue = _escapeJava(value);
			}

			value = value.trim();

			if (!value.startsWith(escapedKey)) {
				valueLines.set(0, escapedKey + " = " + realValue);

				sb.append(escapedKey);
				sb.append(" = ");
				sb.append(realValue);
			}
			else {
				valueLines.set(0, realValue);
				sb.append(realValue);
			}
		}

		for (int i = 1; i < valueLines.size(); i++) {
			String value = valueLines.get(i);

			if (_typed) {
				valueLines.set(i, value);
			}
			else {
				valueLines.set(i, _escapeJava(value));
			}

			while ((value.length() > 0) &&
				   Character.isWhitespace(value.charAt(0))) {

				value = value.substring(1);
			}

			sb.append(value);
		}

		String[] property = PropertiesReader._parseProperty(sb.toString());

		_layoutMap.put(key, new Layout(commentLines, valueLines));

		return _storage.put(key, property[1]);
	}

	public String put(String key, List<String> commentLines, String value) {
		commentLines = new ArrayList<>(commentLines);

		_layoutMap.put(key, new Layout(commentLines, null));

		return _storage.put(key, value);
	}

	@Override
	public String put(String key, String value) {
		String old = _storage.put(key, value);

		if ((old == null) || !old.equals(value)) {
			Layout layout = _layoutMap.get(key);

			if (layout != null) {
				layout.clearValue();
			}
		}

		return old;
	}

	public String put(String key, String comment, String value) {
		return put(key, Collections.singletonList(comment), value);
	}

	public void putAllSubstituted(Map<? extends String, ? extends String> map) {
		_storage.putAll(map);
	}

	@Override
	public String remove(Object key) {
		Layout layout = _layoutMap.get(key);

		if (layout != null) {
			layout.clearValue();
		}

		return _storage.remove(key);
	}

	public void save() throws IOException {
		save(_location);
	}

	public void save(File location) throws IOException {
		try (OutputStream outputStream = new FileOutputStream(location)) {
			save(outputStream);
		}
	}

	public void save(OutputStream outputStream) throws IOException {
		save(new OutputStreamWriter(outputStream, DEFAULT_ENCODING));
	}

	public void save(Writer writer) throws IOException {
		saveLayout(writer, _typed);
	}

	public void setFooter(List<String> footer) {
		_footer = footer;
	}

	public void setHeader(List<String> header) {
		_header = header;
	}

	public Object setProperty(String key, String value) {
		return put(key, value);
	}

	public void setTyped(boolean typed) {
		_typed = typed;
	}

	public void store(OutputStream outputStream, String comment)
		throws IOException {

		save(outputStream);
	}

	public void substitute() {
		substitute(_callback);
	}

	public void substitute(InterpolationUtil.SubstitutionCallback callback) {
		if (callback == null) {
			callback = new InterpolationUtil.BundleContextSubstitutionCallback(
				null);
		}

		InterpolationUtil.performSubstitution(_storage, callback);
	}

	public boolean update(Map<String, String> props) {
		Properties properties = new Properties();

		if (props instanceof Properties) {
			properties = (Properties)props;
		}
		else {
			for (Map.Entry<? extends String, ? extends String> e :
					props.entrySet()) {

				properties.put(e.getKey(), e.getValue());
			}
		}

		return update(properties);
	}

	public boolean update(Properties properties) {
		boolean modified = false;

		// Remove "removed" properties from the cfg file

		for (String key : new ArrayList<>(keySet())) {
			if (!properties.containsKey(key)) {
				remove(key);

				modified = true;
			}
		}

		// Update existing keys

		for (String key : properties.keySet()) {
			String value = get(key);

			List<String> comments = properties.getComments(key);

			List<String> rawValue = properties.getRaw(key);

			if (value == null) {
				put(key, comments, rawValue);

				modified = true;
			}
			else if (!value.equals(properties.get(key))) {
				if (comments.isEmpty()) {
					comments = getComments(key);
				}

				put(key, comments, rawValue);

				modified = true;
			}
		}

		return modified;
	}

	public static class PropertiesReader extends LineNumberReader {

		public PropertiesReader(Reader reader, boolean maybeTyped) {
			super(reader);

			_maybeTyped = maybeTyped;
		}

		public List<String> getCommentLines() {
			return _commentLines;
		}

		public String getPropertyName() {
			return _propertyName;
		}

		public String getPropertyValue() {
			return _propertyValue;
		}

		public List<String> getValueLines() {
			return _valueLines;
		}

		public Boolean isTyped() {
			return _typed;
		}

		public boolean nextProperty() throws IOException {
			String line = readProperty();

			if (line == null) {
				return false; // EOF
			}

			// parse the line

			String[] property = _parseProperty(line);

			boolean typed = false;

			if (_maybeTyped && (property[1].length() >= 2)) {
				Matcher matcher = _pattern.matcher(property[1]);

				typed = matcher.matches();
			}

			if (_typed == null) {
				_typed = typed;
			}
			else {
				_typed = _typed & typed;
			}

			_propertyName = _unescapeJava(property[0]);

			_propertyValue = property[1];

			return true;
		}

		public String readProperty() throws IOException {
			_commentLines.clear();
			_valueLines.clear();

			StringBuffer buffer = new StringBuffer();

			while (true) {
				String line = readLine();

				if (line == null) {

					// EOF

					return null;
				}

				if (_isCommentLine(line)) {
					_commentLines.add(line);

					continue;
				}

				boolean combine = _checkCombineLines(line);

				if (combine) {
					line = line.substring(0, line.length() - 1);
				}

				_valueLines.add(line);

				while ((line.length() > 0) &&
					   contains(_WHITE_SPACE, line.charAt(0))) {

					line = line.substring(1);
				}

				buffer.append(line);

				if (!combine) {
					break;
				}
			}

			return buffer.toString();
		}

		private static boolean _checkCombineLines(String line) {
			int bsCount = 0;

			for (int i = line.length() - 1;
				 (i >= 0) && (line.charAt(i) == '\\'); i--) {

				bsCount++;
			}

			if ((bsCount % 2) != 0) {
				return true;
			}

			return false;
		}

		private static String[] _parseProperty(String line) {

			// sorry for this spaghetti code, please replace it as soon as
			// possible with a regexp when the Java 1.3 requirement is dropped

			String[] result = new String[2];

			StringBuffer key = new StringBuffer();
			StringBuffer value = new StringBuffer();

			// state of the automaton:
			// 0: key parsing
			// 1: antislash found while parsing the key
			// 2: separator crossing
			// 3: white spaces
			// 4: value parsing

			int state = 0;

			for (int pos = 0; pos < line.length(); pos++) {
				char c = line.charAt(pos);

				if (state == 0) {
					if (c == '\\') {
						state = 1;
					}
					else if (contains(_WHITE_SPACE, c)) {

						// switch to the separator crossing state

						state = 2;
					}
					else if (contains(_SEPARATORS, c)) {

						// switch to the value parsing state

						state = 3;
					}
					else {
						key.append(c);
					}
				}
				else if (state == 1) {
					if (contains(_SEPARATORS, c) || contains(_WHITE_SPACE, c)) {

						// this is an escaped separator or white space

						key.append(c);
					}
					else {

						// another escaped character, the '\' is preserved

						key.append('\\');
						key.append(c);
					}

					// return to the key parsing state

					state = 0;
				}
				else if (state == 2) {
					if (contains(_WHITE_SPACE, c)) {

						// do nothing, eat all white spaces

						state = 2;
					}
					else if (contains(_SEPARATORS, c)) {

						// switch to the value parsing state

						state = 3;
					}
					else {

						// any other character indicates we encoutered the
						// beginning of the value

						value.append(c);

						// switch to the value parsing state

						state = 4;
					}
				}
				else if (state == 3) {
					if (contains(_WHITE_SPACE, c)) {

						// do nothing, eat all white spaces

						state = 3;
					}
					else {

						// any other character indicates we encoutered the
						// beginning of the value

						value.append(c);

						// switch to the value parsing state

						state = 4;
					}
				}
				else if (state == 4) {
					value.append(c);
				}
			}

			result[0] = key.toString();
			result[1] = value.toString();

			return result;
		}

		private final List<String> _commentLines = new ArrayList<>();
		private final boolean _maybeTyped;
		private Pattern _pattern = Pattern.compile(
			"\\s*[TILFDXSCBilfdxscb]?(\\[[\\S\\s]*\\]|\\{[\\S\\s]*\\}|" +
				"\"[\\S\\s]*\")\\s*");
		private String _propertyName;
		private String _propertyValue;
		private Boolean _typed;
		private final List<String> _valueLines = new ArrayList<>();

	}

	public static class PropertiesWriter extends FilterWriter {

		public PropertiesWriter(Writer writer, boolean typed) {
			super(writer);

			_typed = typed;
		}

		public void writeln(String string) throws IOException {
			if (string != null) {
				write(string);
			}

			write(_LINE_SEPARATOR);
		}

		public void writeProperty(String key, String value) throws IOException {
			write(_escapeKey(key));
			write(" = ");
			write(_typed ? value : _escapeJava(value));
			writeln(null);
		}

		private boolean _typed;

	}

	protected void saveLayout(Writer writer, boolean typed) throws IOException {
		try (PropertiesWriter propertiesWriter = new PropertiesWriter(
				writer, typed)) {

			if (_header != null) {
				for (String s : _header) {
					propertiesWriter.writeln(s);
				}
			}

			for (Entry<String, String> entry : _storage.entrySet()) {
				String key = entry.getKey();

				String value = entry.getValue();

				Layout layout = _layoutMap.get(key);

				if (layout == null) {
					propertiesWriter.writeProperty(key, value);

					continue;
				}

				List<String> commentLines = layout.getCommentLines();

				if (commentLines != null) {
					for (String string : commentLines) {
						propertiesWriter.writeln(string);
					}
				}

				List<String> valueLines = layout.getValueLines();

				if (valueLines == null) {
					propertiesWriter.writeProperty(key, value);

					continue;
				}

				int size = valueLines.size();

				for (int i = 0; i < size; i++) {
					String string = valueLines.get(i);

					if (i < (size - 1)) {
						propertiesWriter.writeln(string + "\\");
					}
					else {
						propertiesWriter.writeln(string);
					}
				}
			}

			if (_footer != null) {
				for (String string : _footer) {
					propertiesWriter.writeln(string);
				}
			}
		}
	}

	private static String _escapeJava(String string) {
		if (string == null) {
			return null;
		}

		int length = string.length();

		StringBuffer stringBuffer = new StringBuffer(length * 2);

		for (int i = 0; i < length; i++) {
			char c = string.charAt(i);

			// handle unicode

			if (c > 0xfff) {
				stringBuffer.append("\\u");
				stringBuffer.append(_hex(c));
			}
			else if (c > 0xff) {
				stringBuffer.append("\\u0");
				stringBuffer.append(_hex(c));
			}
			else if (c > 0x7f) {
				stringBuffer.append("\\u00");
				stringBuffer.append(_hex(c));
			}
			else if (c < 32) {
				if (c == 'b') {
					stringBuffer.append('\\');
					stringBuffer.append('b');
				}
				else if (c == 'n') {
					stringBuffer.append('\\');
					stringBuffer.append('n');
				}
				else if (c == 't') {
					stringBuffer.append('\\');
					stringBuffer.append('t');
				}
				else if (c == 'f') {
					stringBuffer.append('\\');
					stringBuffer.append('f');
				}
				else if (c == 'r') {
					stringBuffer.append('\\');
					stringBuffer.append('r');
				}
				else {
					if (c > 0xf) {
						stringBuffer.append("\\u00");
						stringBuffer.append(_hex(c));
					}
					else {
						stringBuffer.append("\\u000");
						stringBuffer.append(_hex(c));
					}
				}
			}
			else {
				if (c == '"') {
					stringBuffer.append('\\');
					stringBuffer.append('"');
				}
				else if (c == '\\') {
					stringBuffer.append('\\');
					stringBuffer.append('\\');
				}
				else {
					stringBuffer.append(c);
				}
			}
		}

		return stringBuffer.toString();
	}

	private static String _escapeKey(String key) {
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i);

			if (contains(_SEPARATORS, c) || contains(_WHITE_SPACE, c)) {

				// escape the separator

				stringBuffer.append('\\');
				stringBuffer.append(c);
			}
			else {
				stringBuffer.append(c);
			}
		}

		return stringBuffer.toString();
	}

	private static String _hex(char ch) {
		String hexString = Integer.toHexString(ch);

		return hexString.toUpperCase(LocaleUtil.ENGLISH);
	}

	private static boolean _isCommentLine(String line) {
		String string = line.trim();

		// blank lines are also treated as comment lines

		if ((string.length() < 1) ||
			(_COMMENT_CHARS.indexOf(string.charAt(0)) >= 0)) {

			return true;
		}

		return false;
	}

	private static String _unescapeJava(String string) {
		if (string == null) {
			return null;
		}

		int size = string.length();

		StringBuffer stringBuffer = new StringBuffer(size);

		StringBuffer unicode = new StringBuffer(_UNICODE_LEN);

		boolean hadSlash = false;

		boolean inUnicode = false;

		for (int i = 0; i < size; i++) {
			char c = string.charAt(i);

			if (inUnicode) {

				// if in unicode, then we're reading unicode
				// values in somehow

				unicode.append(c);

				if (unicode.length() == _UNICODE_LEN) {

					// unicode now contains the four hex digits
					// which represents our unicode character

					try {
						int value = Integer.parseInt(
							unicode.toString(), _HEX_RADIX);

						stringBuffer.append((char)value);

						unicode.setLength(0);

						inUnicode = false;

						hadSlash = false;
					}
					catch (NumberFormatException numberFormatException) {
						throw new IllegalArgumentException(
							"Unable to parse unicode value: " + unicode,
							numberFormatException);
					}
				}

				continue;
			}

			if (hadSlash) {

				// handle an escaped value

				hadSlash = false;

				if (c == '\\') {
					stringBuffer.append("\\");
				}
				else if (c == '\'') {
					stringBuffer.append('\'');
				}
				else if (c == '"') {
					stringBuffer.append('"');
				}
				else if (c == 'r') {
					stringBuffer.append('\r');
				}
				else if (c == 'f') {
					stringBuffer.append('\f');
				}
				else if (c == 't') {
					stringBuffer.append('\t');
				}
				else if (c == 'n') {
					stringBuffer.append('\n');
				}
				else if (c == 'b') {
					stringBuffer.append('\b');
				}
				else if (c == 'u') {
					inUnicode = true;
				}
				else {
					stringBuffer.append(c);
				}

				continue;
			}
			else if (c == '\\') {
				hadSlash = true;

				continue;
			}

			stringBuffer.append(c);
		}

		if (hadSlash) {

			// then we're in the weird case of a \ at the end of the
			// string, let's output it anyway.

			stringBuffer.append('\\');
		}

		return stringBuffer.toString();
	}

	private int _checkHeaderComment(List<String> commentLines) {
		if ((getHeader() == null) && _layoutMap.isEmpty()) {

			// This is the first comment. Search for blank lines.

			int index = commentLines.size() - 1;

			while (index >= 0) {
				String commentLine = commentLines.get(index);

				if (commentLine.length() <= 0) {
					break;
				}

				index--;
			}

			setHeader(new ArrayList<>(commentLines.subList(0, index + 1)));

			return index + 1;
		}

		return 0;
	}

	private static final String _COMMENT_CHARS = "#!";

	private static final int _HEX_RADIX = 16;

	private static final String _LINE_SEPARATOR = System.getProperty(
		"line.separator");

	private static final char[] _SEPARATORS = {'=', ':'};

	private static final int _UNICODE_LEN = 4;

	private static final char[] _WHITE_SPACE = {' ', '\t', '\f'};

	private InterpolationUtil.SubstitutionCallback _callback;
	private List<String> _footer;
	private List<String> _header;
	private final Map<String, Layout> _layoutMap = new LinkedHashMap<>();
	private File _location;
	private final Map<String, String> _storage = new LinkedHashMap<>();
	private boolean _substitute = true;
	private boolean _typed;

	private static class Layout {

		public Layout() {
		}

		public Layout(List<String> commentLines, List<String> valueLines) {
			_commentLines = commentLines;
			_valueLines = valueLines;
		}

		public void clearValue() {
			_valueLines = null;
		}

		public List<String> getCommentLines() {
			return _commentLines;
		}

		public List<String> getValueLines() {
			return _valueLines;
		}

		public void setCommentLines(List<String> commentLines) {
			_commentLines = commentLines;
		}

		public void setValueLines(List<String> valueLines) {
			_valueLines = valueLines;
		}

		private List<String> _commentLines;
		private List<String> _valueLines;

	}

	private class KeyIterator implements Iterator<Entry<String, String>> {

		public KeyIterator() {
			Set<Entry<String, String>> entries = _storage.entrySet();

			_iterator = entries.iterator();
		}

		@Override
		public boolean hasNext() {
			return _iterator.hasNext();
		}

		@Override
		public Entry<String, String> next() {
			final Entry<String, String> entry = _iterator.next();

			return new Entry<String, String>() {

				@Override
				public String getKey() {
					return entry.getKey();
				}

				@Override
				public String getValue() {
					return entry.getValue();
				}

				@Override
				public String setValue(String value) {
					String old = entry.setValue(value);

					if ((old == null) || !old.equals(value)) {
						Layout layout = _layoutMap.get(entry.getKey());

						if (layout != null) {
							layout.clearValue();
						}
					}

					return old;
				}

			};
		}

		@Override
		public void remove() {
			_iterator.remove();
		}

		private final Iterator<Entry<String, String>> _iterator;

	}

}