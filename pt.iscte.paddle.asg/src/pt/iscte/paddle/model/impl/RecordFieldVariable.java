package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordFieldVariable;
import pt.iscte.paddle.model.IVariable;

public class RecordFieldVariable extends Variable implements IRecordFieldVariable {
	private final IVariable field;
	
	public RecordFieldVariable(IProgramElement parent, IVariable field) {
		super(parent, field.getType());
		this.field = field;
	}

	@Override
	public IVariable getField() {
		return field;
	}
	
	@Override
	public String getId() {
		return getParent().getId() + "." + field.getId();
	}


}
