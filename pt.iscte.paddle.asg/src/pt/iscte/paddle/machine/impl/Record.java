package pt.iscte.paddle.machine.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IRecord;
import pt.iscte.paddle.machine.IValue;

public class Record implements IRecord {
	private final IRecordType type;
	private final Map<IVariable, IValue> fields;
	
	public Record(IRecordType type) {
		this.type = type;
		this.fields = new LinkedHashMap<>();
		for (IVariable var : type.getFields()) {
			IValue val = Value.create(var.getType(), var.getType().getDefaultValue());
			fields.put(var, val);
		}
	}
	
	@Override
	public IValue getField(IVariable field) {
		return fields.get(field);
	}
	
	@Override
	public void setField(IVariable field, IValue value) {
		fields.put(field, value);
	}
	
	@Override
	public String toString() {
		String text = "";
		for (Entry<IVariable, IValue> e : fields.entrySet()) {
			text += e.getKey() + " = " + e.getValue() + "\n";
		}
		return text;
	}

	@Override
	public IType getType() {
		return type;
	}

	@Override
	public Map<IVariable, IValue> getValue() {
		return Collections.unmodifiableMap(fields);
	}
	
	@Override
	public IRecord copy() {
		Record record = new Record(type);
		for (IVariable var : type.getFields())
			record.fields.put(var, getField(var).copy());
		return record;
	}
}
