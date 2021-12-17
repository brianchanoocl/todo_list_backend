import { useState } from "react";
import { useDispatch } from "react-redux";
import { UPDATE_TODOLIST } from "../constants/constants";
import { addTodoItem } from "../APIs/todos";
import { Button, Input } from "antd";


function TodoGenerator() {
    const [todoItemDetail, setTodoItemDetail] = useState("");
    const dispatch = useDispatch();

    function handleTodoItemDetailChange(event){
       setTodoItemDetail(event.target.value);
    }

    function handleAddTodoItem(){
        if(todoItemDetail.trim() === "")
            alert("dun be lazy, do some work la... = =");
        else{
            addTodoItem({ text: todoItemDetail, done: false }).then((response) => {
                dispatch({type: UPDATE_TODOLIST, payload: response.data});
            });
        }
        setTodoItemDetail("");
    }

    return(
        <div className="center-item row-item center-text">
            <Input className="row-item" value={todoItemDetail} placeholder="Please add your task here..." onChange={handleTodoItemDetailChange}></Input>
            <Button type="primary" className="row-item" onClick={handleAddTodoItem}>Add</Button>
        </div>
    );
}

export default TodoGenerator;