package exceptions.baseservice;

import exceptions.ServiceException;

public class CannotDeserializeObjectsException extends ServiceException  {
	private static final long serialVersionUID = 2821612721097864859L;
	
	@SuppressWarnings("rawtypes")
	public Class clazz;
	
	public CannotDeserializeObjectsException(@SuppressWarnings("rawtypes") Class clazz) {
		super(String.format("Cannot deserialize objects of class %1$s", clazz.getName()));
		this.clazz = clazz;
	}
	
	@Override
	public String getCode() {
		String code = "cannot_deserialize_objects";
		return code;
	}

	@Override
	public String classNameThrown() {
		String classNameThrown = "AbstractBaseService";
		return classNameThrown;
	}	
}