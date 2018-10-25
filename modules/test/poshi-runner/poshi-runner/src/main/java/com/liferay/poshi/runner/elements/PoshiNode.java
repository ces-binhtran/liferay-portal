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

package com.liferay.poshi.runner.elements;

import com.liferay.poshi.runner.script.PoshiScriptParserException;
import com.liferay.poshi.runner.util.StringUtil;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;

import org.dom4j.Node;

/**
* @author Michael Hashimoto
 */
public interface PoshiNode<A extends Node, B extends PoshiNode<A, B>>
	extends Node {

	public B clone(A node);

	public B clone(String poshiScript) throws PoshiScriptParserException;

	public default String getFilePath() {
		PoshiNode parentPoshiNode = (PoshiNode)getParent();

		return parentPoshiNode.getFilePath();
	}

	public default String getFileType() {
		PoshiNode parentPoshiNode = (PoshiNode)getParent();

		return parentPoshiNode.getFileType();
	}

	public String getPoshiScript();

	public default int getPoshiScriptLineNumber() {
		PoshiElement parentPoshiElement = (PoshiElement)getParent();

		if (parentPoshiElement == null) {
			return 1;
		}

		List<PoshiNode> poshiNodes = parentPoshiElement.getPoshiNodes();

		PoshiNode previousPoshiNode = null;

		for (Iterator<PoshiNode> iterator = poshiNodes.iterator();
			 iterator.hasNext();) {

			PoshiNode poshiNode = iterator.next();

			if (poshiNode instanceof DescriptionPoshiElement) {
				continue;
			}

			if (poshiNode.equals(this)) {
				if (previousPoshiNode == null) {
					String parentPoshiScript =
						parentPoshiElement.getPoshiScript();

					Matcher poshiScriptBlockMatcher =
						PoshiElement.poshiScriptBlockPattern.matcher(
							parentPoshiScript);

					if (poshiScriptBlockMatcher.find()) {
						String blockName = parentPoshiElement.getBlockName(
							parentPoshiScript);

						return parentPoshiElement.getPoshiScriptLineNumber() +
							StringUtil.count(blockName, "\n") +
								StringUtil.countStartingNewLines(
									getPoshiScript());
					}

					return parentPoshiElement.getPoshiScriptLineNumber();
				}

				String previousPoshiScript = previousPoshiNode.getPoshiScript();

				String poshiScript = poshiNode.getPoshiScript();

				return previousPoshiNode.getPoshiScriptLineNumber() -
					StringUtil.countStartingNewLines(previousPoshiScript) +
						StringUtil.countStartingNewLines(poshiScript) +
							StringUtil.count(previousPoshiScript, "\n");
			}

			previousPoshiNode = poshiNode;
		}

		return previousPoshiNode.getPoshiScriptLineNumber() +
			StringUtil.count(previousPoshiNode.getPoshiScript(), "\n");
	}

	public void parsePoshiScript(String poshiScript)
		throws PoshiScriptParserException;

	public void setPoshiScript(String poshiScript);

	public String toPoshiScript();

}