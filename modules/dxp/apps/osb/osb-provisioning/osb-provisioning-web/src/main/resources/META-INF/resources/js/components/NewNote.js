import PropTypes from 'prop-types';
import React, {useState} from 'react';

const NAMESPACE = window.ProvisioningConstants.namespace;
const NOTE_STATUS_APPROVED = window.ProvisioningConstants.noteStatus.approved;
const NOTE_TYPE_GENERAL = window.ProvisioningConstants.noteType.general;

function NewNote({addURL}) {
	const [noteContent, setNoteContent] = useState('');
	const [showButtons, setShowButtons] = useState(false);

	return (
		<form action={addURL} className="add-new-note" method="post">
			<input name={`${NAMESPACE}priority`} type="hidden" value={2} />
			<input
				name={`${NAMESPACE}status`}
				type="hidden"
				value={NOTE_STATUS_APPROVED}
			/>
			<input
				name={`${NAMESPACE}type`}
				type="hidden"
				value={NOTE_TYPE_GENERAL}
			/>

			<label className="form-control-label" htmlFor="addNoteContent">
				<textarea
					className="form-control"
					id="addNoteContent"
					name={`${NAMESPACE}content`}
					onChange={event =>
						setNoteContent(event.currentTarget.value)
					}
					onClick={() => setShowButtons(true)}
					placeholder={Liferay.Language.get('write-a-note')}
					value={noteContent}
				/>
			</label>

			{showButtons && (
				<div className="button-row">
					<button
						className="btn btn-secondary cancel-btn"
						onClick={() => setNoteContent('')}
						role="button"
						type="button"
					>
						{Liferay.Language.get('cancel')}
					</button>

					<button
						className="btn btn-primary save-btn"
						role="button"
						type="submit"
					>
						{Liferay.Language.get('save')}
					</button>
				</div>
			)}
		</form>
	);
}

NewNote.propTypes = {
	addURL: PropTypes.string
};

export default NewNote;
