package com.ing.cashual.chatboting.ai.rasa;

import com.ing.cashual.chatboting.ai.rasa.model.RasaNextAction;

public interface ActionProcessor {

	String getActionName();

	String performAction(RasaNextAction nextAction);
}
