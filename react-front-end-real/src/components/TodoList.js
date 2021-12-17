import { MessageOutlined } from '@ant-design/icons/lib/icons';
import TodoGenerator from './TodoGenerator';
import TodoGroup from './TodoGroup';

function TodoList() {
    return(
        <div className="main-page">
            <h1 className="center-text"><MessageOutlined /> Todo List <MessageOutlined /></h1>
            <TodoGenerator></TodoGenerator>
            <TodoGroup></TodoGroup>
        </div>
    );
}

export default TodoList;