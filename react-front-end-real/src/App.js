import './App.css';
import TodoList from './components/TodoList';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import DoneItems from './components/DoneItems';
import UndoneItems from './components/UndoneItems';
import { useEffect } from 'react';
import { getTodos } from './APIs/todos';
import { useDispatch } from 'react-redux';
import { INIT_TODO } from './constants/constants';
import { Button } from 'antd';
import { AlertOutlined, CoffeeOutlined, HomeOutlined } from '@ant-design/icons/lib/icons';

function App() {
  const dispatch = useDispatch();

    useEffect(() => {
        getTodos().then((response) => {
            console.log(response)
            dispatch({type: INIT_TODO, payload: response.data});
        })
    });

  return (
    <div>
      <Router>
      <div>
        <nav>
              <Button type="text" className='link'><Link to="/"><HomeOutlined /> Home</Link></Button>
              <Button type="text" className='link'><Link to="/done"><CoffeeOutlined /> Done</Link></Button>
              <Button type="text" className='link'><Link to="/undone"><AlertOutlined /> Undone</Link></Button>
        </nav>
        <Switch>
          <Route exact path="/done">
            <DoneItems />
          </Route>
          <Route exact path="/undone">
            <UndoneItems />
          </Route>
          <Route exact path="/">
            <Home />
          </Route>
        </Switch>
      </div>
    </Router>
    </div>
  );
}

function Home() {
  return <TodoList></TodoList>;
}

export default App;
