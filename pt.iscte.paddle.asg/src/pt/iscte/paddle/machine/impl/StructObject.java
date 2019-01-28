package pt.iscte.paddle.machine.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IStructType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.IStructObject;
import pt.iscte.paddle.machine.IValue;

public class StructObject implements IStructObject {

	private final IStructType type;
	private Map<String, IValue> fields;
	
	public StructObject(IStructType type) {
		this.type = type;
		fields = new LinkedHashMap<>();
		for (IVariable var : type.getMemberVariables()) {
			fields.put(var.getId(), Value.create(var.getType(), var.getType().getDefaultValue()));
		}
	}
	
	@Override
	public IValue getField(String id) {
		return fields.get(id);
	}
	
	@Override
	public void setField(String id, IValue value) {
		// TODO check id
		fields.replace(id, value);
	}
	
	@Override
	public String toString() {
		String text = "";
		for (Entry<String, IValue> e : fields.entrySet()) {
			text += e.getKey() + " = " + e.getValue() + "\n";
		}
		return text;
	}

	@Override
	public IDataType getType() {
		return type;
	}

	@Override
	public Object getValue() {
		// TODO array?
		return null;
	}
	
	@Override
	public void setValue(Object o) {
		// TODO ??
		
	}
	
	@Override
	public IValue copy() {
		// TODO Auto-generated method stub
		return null;
	}
}
