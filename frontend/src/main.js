import Vue from 'vue'
import BootstrapVue from "bootstrap-vue"
import App from './App.vue'
import router from './router'

// https://bootstrap-vue.js.org/
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap-vue/dist/bootstrap-vue.css"

import VModal from 'vue-js-modal/dist/index.nocss.js'
import 'vue-js-modal/dist/styles.css'

import VueSession from 'vue-session'
import vuetify from './plugins/vuetify';

import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import enUS from '@kangc/v-md-editor/lib/lang/en-US';

import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';
// Introduce the theme you use. Take the github theme as an example here
import githubTheme from '@kangc/v-md-editor/lib/theme/github';
VMdPreview.use(githubTheme);
Vue.use(VMdPreview);


//markdown ediotr, viewer
import { Editor, Viewer } from '@toast-ui/vue-editor'
Vue.component('editor', Editor)
Vue.component('viewer', Viewer)


VueMarkdownEditor.lang.use('en-US', enUS);
VueMarkdownEditor.use(vuepressTheme);


Vue.use(VueMarkdownEditor);
var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions)
Vue.use(BootstrapVue);

Vue.use(VModal, {
  dynamicDefaults: {
    draggable: true,
    resizable: true,
    height: 'auto'
  }
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  vuetify,

  data: {
    showModal: false
  }
})

//  new Vue({
//   el: '#app',
//   vuetify: new Vuetify(),
//   data () {
//     return {
//       page: 1,
//     }
//   },
// })

