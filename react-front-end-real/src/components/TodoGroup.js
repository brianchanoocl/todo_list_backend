import { useSelector } from 'react-redux';
import TodoItem from "./TodoItem";

function TodoGroup() {
    const todoItems = useSelector(state => state.todoItems);

    return(
        <div className="center-item">
            {
                todoItems.map((item, index) => {
                   return <TodoItem todoItem={item} key={item+index}></TodoItem>
                })
            }
        </div>
    );
}

export default TodoGroup;