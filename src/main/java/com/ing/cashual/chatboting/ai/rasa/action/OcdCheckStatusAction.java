package com.ing.cashual.chatboting.ai.rasa.action;

import com.ing.cashual.chatboting.ai.rasa.ActionProcessor;
import com.ing.cashual.chatboting.ai.rasa.model.OCDControlInfo;
import com.ing.cashual.chatboting.ai.rasa.model.RasaNextAction;
import com.ing.cashual.chatboting.ai.rasa.model.RasaParseBody;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OcdCheckStatusAction implements ActionProcessor {

	public static String APP_ID = "application-name";
	public static String CTRL_ID = "control-name";

	@Value("${ocd-server.address}")
	private String ocdServerAddress;


	private final RestTemplate restTemplate;

	public OcdCheckStatusAction(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public String getActionName() {
		return "utter_status";
	}

	@Override
	public String performAction(RasaNextAction rasaNextAction) {
		String appId = rasaNextAction.getTracker().getSlots().get(APP_ID);
		String ctrlId = rasaNextAction.getTracker().getSlots().get(CTRL_ID);
		String url = ocdServerAddress + "/control/" + appId + "/" + ctrlId;
		OCDControlInfo response = restTemplate.getForObject(url, OCDControlInfo.class);
		return "Your control is: " + response;
	}
}
