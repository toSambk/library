package jdbcTask.factory;

import jdbcTask.factory.addNewAction.AddNewAction;
import jdbcTask.factory.addNewAction.AddNewPublisherAction;
import jdbcTask.factory.deleteAction.DeleteAction;
import jdbcTask.factory.deleteAction.DeletePublisherAction;
import jdbcTask.factory.getAllAction.GetAllAction;
import jdbcTask.factory.getAllAction.GetAllPublishersAction;
import jdbcTask.factory.updateAction.UpdateAction;
import jdbcTask.factory.updateAction.UpdatePublisherAction;

public class PublisherActionsFactory implements AbstractActionsFactory {

    @Override
    public AddNewAction getAddNewAction() {
        return new AddNewPublisherAction();
    }

    @Override
    public DeleteAction getDeleteAction() {
        return new DeletePublisherAction();
    }

    @Override
    public GetAllAction getAllAction() {
        return new GetAllPublishersAction();
    }

    @Override
    public UpdateAction getUpdateAction() {
        return new UpdatePublisherAction();
    }

}
