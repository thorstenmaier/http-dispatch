package demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.actions.Action;
import demo.handler.ActionHandler;

@RestController
public class RestDispatchController {

	@Autowired
	private List<ActionHandler<?>> allActionHandlers;

	private Map<Class<?>, ActionHandler<Action>> actionHandlerMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		for (ActionHandler<?> handler : allActionHandlers) {
			actionHandlerMap.put(handler.getActionType(), (ActionHandler<Action>) handler);
		}
	}

	@PostMapping("/dispatch")
	public String dispatch(@RequestBody String json) throws IOException, ClassNotFoundException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String actionClassName = objectMapper.readTree(json).get("type").asText();
		Action actionObject = (Action) objectMapper.readValue(json, Class.forName(actionClassName));
		ActionHandler<Action> actionHandler = actionHandlerMap.get(actionObject.getClass());
		return actionHandler.execute(actionObject);
	}

}
