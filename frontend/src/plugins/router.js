import { createRouter, createWebHistory } from 'vue-router';
import NoteView from '../components/view/NoteView';
import LoginView from '../components/view/LoginView';

const routes = [
    { path: '/note', component : NoteView },
    { path: '/', component: LoginView },
];

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;