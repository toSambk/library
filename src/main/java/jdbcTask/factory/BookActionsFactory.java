package jdbcTask.factory;

import jdbcTask.factory.addNewAction.AddNewAction;
import jdbcTask.factory.addNewAction.AddNewBookAction;
import jdbcTask.factory.deleteAction.DeleteAction;
import jdbcTask.factory.deleteAction.DeleteBookAction;
import jdbcTask.factory.getAllAction.GetAllAction;
import jdbcTask.factory.getAllAction.GetAllBooksAction;
import jdbcTask.factory.updateAction.UpdateAction;
import jdbcTask.factory.updateAction.UpdateBookAction;

public class BookActionsFactory implements AbstractActionsFactory {

    @Override
    public AddNewAction getAddNewAction() {
        return new AddNewBookAction();
    }

    @Override
    public DeleteAction getDeleteAction() {
        return new DeleteBookAction();
    }

    @Override
    public GetAllAction getAllAction() {
        return new GetAllBooksAction();
    }

    @Override
    public UpdateAction getUpdateAction() {
        return new UpdateBookAction();
    }
}
