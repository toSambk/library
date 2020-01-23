package jdbcTask.factory;

import jdbcTask.factory.addNewAction.AddNewAction;
import jdbcTask.factory.addNewAction.AddNewAuthorAction;
import jdbcTask.factory.deleteAction.DeleteAction;
import jdbcTask.factory.deleteAction.DeleteAuthorAction;
import jdbcTask.factory.getAllAction.GetAllAction;
import jdbcTask.factory.getAllAction.GetAllAuthorsAction;
import jdbcTask.factory.updateAction.UpdateAction;
import jdbcTask.factory.updateAction.UpdateAuthorAction;

public class AuthorActionsFactory implements AbstractEntityFactory {
    @Override
    public AddNewAction getAddNewAction() {
        return new AddNewAuthorAction();
    }

    @Override
    public DeleteAction getDeleteAction() {
        return new DeleteAuthorAction();
    }

    @Override
    public GetAllAction getAllAction() {
        return new GetAllAuthorsAction();
    }

    @Override
    public UpdateAction getUpdateAction() {
        return new UpdateAuthorAction();
    }
}
