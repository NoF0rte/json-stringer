package burp;

import com.fasterxml.jackson.databind.ObjectMapper;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.intruder.PayloadData;
import burp.api.montoya.intruder.PayloadProcessingResult;
import burp.api.montoya.intruder.PayloadProcessor;

public class JsonStringer implements PayloadProcessor {

	private MontoyaApi api;

	public JsonStringer(MontoyaApi api) {
		this.api = api;
	}

	@Override
	public String displayName() {
		return "JSON Stringer";
	}

	@Override
	public PayloadProcessingResult processPayload(PayloadData payloadData) {
		// ObjectMapper mapper = new ObjectMapper();

		// String payload = new Gson().toJson(payloadData.currentPayload().toString());
		try {
			String payload = new ObjectMapper().writeValueAsString(payloadData.currentPayload().toString());
			payload = payload.substring(1, payload.length() - 1);
			
			return PayloadProcessingResult.usePayload(ByteArray.byteArray(payload));
		} catch (Exception e) {
			api.logging().logToError(e.getMessage());
		}

		return PayloadProcessingResult.usePayload(payloadData.currentPayload());
	}
}
