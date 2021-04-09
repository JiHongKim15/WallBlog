<template>
  <div id="app">
    <Header :isHeader="isHeader" />
    <div style="margin-top:3%; margin-bottom:3%">
      <router-view/>
    </div>
    <!-- <v-footer app height="60px">
      <span class="footer_s">&copy; {{ new Date().getFullYear() }} 이 블로그의 저작권은 싸피탈출넘버원에있습니다.</span>
    </v-footer> -->
  </div>
</template>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>  

<script>
import "./assets/css/style.scss";
import Header from "./components/common/Header.vue";
import constants from "./lib/constants";
export default {
  plugins: ["~plugins/vue-js-modal.js"],
  name: "App",
  components: {
    Header,
  },
  created() {
    let url = this.$route.name;

    this.checkUrl(url);
  },
  watch: {
    $route(to) {
      this.checkUrl(to.name);
    },
  },
  methods: {
    checkUrl(url) {
      let array = [
        constants.URL_TYPE.USER.LOGIN,
        constants.URL_TYPE.USER.JOIN,
        constants.URL_TYPE.USER.UPDATE,
      ];

      let isHeader = true;
      array.map((path) => {
        if (url === path) isHeader = false;
      });
      this.isHeader = isHeader;
    },
  },
  data: function () {
    return {
      isHeader: true,
      constants,
    };
  },
};
</script>

<style>
