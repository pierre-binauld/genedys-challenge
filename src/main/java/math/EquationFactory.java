package math;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EquationFactory {

	private static final String TYPE  = "type";
	private static final String VALUE = "value";

	public Equation fromJson(String json) {

		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		
		return fromJson(jsonObject);
	}
	
	public Equation fromJson(JsonObject json) {

		String 		type  = json.get(TYPE).getAsString();
		
		JsonElement value = json.get(VALUE);

		Equation result = null;
		
		if (Type.PLUS.getType().equals(type)) {
			Equation eq1 = fromJson(value.getAsJsonArray().get(0).getAsJsonObject());
			Equation eq2 = fromJson(value.getAsJsonArray().get(1).getAsJsonObject());
			result = new OperatorEquation(Operator.PLUS, eq1, eq2);
			
			
		} else if (Type.MINUS.getType().equals(type)) {
			Equation eq1 = fromJson(value.getAsJsonArray().get(0).getAsJsonObject());
			Equation eq2 = fromJson(value.getAsJsonArray().get(1).getAsJsonObject());
			result = new OperatorEquation(Operator.MINUS, eq1, eq2);
			
			
		} else if (Type.MULTIPLY.getType().equals(type)) {
			Equation eq1 = fromJson(value.getAsJsonArray().get(0).getAsJsonObject());
			Equation eq2 = fromJson(value.getAsJsonArray().get(1).getAsJsonObject());
			result = new OperatorEquation(Operator.MULTIPLY, eq1, eq2);
			
			
		} else if (Type.INT.getType().equals(type)) {
			int num = value.getAsInt();
			result = new IntEquation(num);
			
		} else if (Type.FLOAT.getType().equals(type)) {
			float num = value.getAsFloat();
			result = new FloatEquation(num);
			
		}
		
		return result;
	}
	
	public JsonObject toJson(float result) {
		JsonObject json = new JsonObject();
		
		json.addProperty("result", result);
		
		return json;
	}
}
