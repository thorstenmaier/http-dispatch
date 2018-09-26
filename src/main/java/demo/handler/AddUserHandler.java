package demo.handler;

import org.springframework.stereotype.Component;

import demo.actions.AddUserAction;

@Component
public class AddUserHandler implements ActionHandler<AddUserAction> {

	@Override
	public String execute(AddUserAction action) {
		return "Added user: " + action.getFirstname() + " " + action.getLastname();
	}

	@Override
	public Class<AddUserAction> getActionType() {
		return AddUserAction.class;
	}

}
