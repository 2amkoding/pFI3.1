import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { LoginForm } from '@/components/login-form'
import './App.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginForm />} />
        <Route path="/" element={<Navigate to="/login" />} /> 
      </Routes>
    </BrowserRouter>
  )
}

export default App