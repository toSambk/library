package jdbcTask.factory;

import jdbcTask.factory.addNewAction.AddNewAction;
import jdbcTask.factory.deleteAction.DeleteAction;
import jdbcTask.factory.getAllAction.GetAllAction;
import jdbcTask.factory.updateAction.UpdateAction;

public interface AbstractEntityFactory {

    AddNewAction getAddNewAction();

    DeleteAction getDeleteAction();

    GetAllAction getAllAction();

    UpdateAction getUpdateAction();

}
