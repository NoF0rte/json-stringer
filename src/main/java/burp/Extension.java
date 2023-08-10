package burp;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class Extension implements BurpExtension {

	private MontoyaApi api;

	@Override
	public void initialize(MontoyaApi api) {
		this.api = api;

		api.extension().setName("JSON Stringer");
		api.intruder().registerPayloadProcessor(new JsonStringer(api));
	}
}
