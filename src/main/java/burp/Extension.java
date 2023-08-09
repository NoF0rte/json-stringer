package burp;

import com.google.gson.Gson;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.intruder.PayloadData;
import burp.api.montoya.intruder.PayloadProcessingResult;
import burp.api.montoya.intruder.PayloadProcessor;

public class Extension implements BurpExtension, PayloadProcessor {

	private MontoyaApi api;

	@Override
	public void initialize(MontoyaApi api) {
		this.api = api;

		api.extension().setName("JSON Stringer");
		api.intruder().registerPayloadProcessor(this);
	}

	@Override
	public String displayName() {
		return "JSON Stringer";
	}

	@Override
	public PayloadProcessingResult processPayload(PayloadData payloadData) {
		String payload = new Gson().toJson(payloadData.currentPayload().toString());
		payload = payload.substring(1, payload.length() - 1);
		
		return PayloadProcessingResult.usePayload(ByteArray.byteArray(payload));
	}
}
