package demo.handler;

import demo.actions.Action;

public interface ActionHandler<T extends Action> {

	String execute(T action);

	Class<T> getActionType();
}
