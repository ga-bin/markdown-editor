/**
 * plugins/index.js
 *
 * Automatically included in `./src/main.js`
 */

// Plugins
import vuetify from './vuetify'
import axios from "axios";
import { createPinia } from 'pinia'
import InfiniteLoading from "v3-infinite-loading";
import "v3-infinite-loading/lib/style.css";
import router from './router';


export function registerPlugins (app) {
  app.use(vuetify)
  app.use(createPinia());
  app.use(router);
  app.component("infinite-loading", InfiniteLoading);
}
