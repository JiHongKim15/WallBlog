<template>
  <div>
    <div class="searchbar">
      <div class="flexbox">
        <div class="search">
          <h1 style="font-size:40px; height:80px;  font-family: Roboto, sans-serif;">SEARCH YOUR FUTURE</h1>
          <p style="text-align:center; font-size:20px; height:40px; margin-top:30px;  font-family: Roboto, sans-serif;">Click search icon, Enter company</p>
          <hr />

          <div>
            <input
              type="text"
              placeholder="Search"
              required
              @keyup.enter="search"
              v-model="keyword"
              style="margin-top: 10px;"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import constants from "../../lib/constants";
import axios from "@/util/axios";

export default {
  props: {
    source: String,
  },
  data: () => ({
    drawer: null,
    constants,
    email: "",
    password: "",
    loginData: {
      email: "",
      password: "",
    },
    keyword: "",
    InfoOfficial: {},
    
  }),

  name: "MyComponent",
  methods: {
    show() {
      this.$modal.show("my-first-modal");
    },
    hide() {
      this.$modal.hide("my-first-modal");
    },
    signin: function () {
      axios({
        method: "POST",
        url: "/login",
        data: {
          password: this.loginData.password,
          email: this.loginData.email,
        },
      })
        .then((Response) => {
          this.$session.set("uid", Response.data);
          this.$modal.hide("my-first-modal");
          this.$router.go();
        })
        .catch((ex) => {
          var errorcode = ex.response.status;
          if (errorcode == 400) {
            alert("로그인에 실패하였습니다.");
          } else {
            this.$router.push("/errorpage/" + errorcode);
          }
        });
    },
    mypage: function () {
      axios({
        method: "GET",
        url: "/user/update",
        data: {
          email: this.email,
        },
      })
        .then((Response) => {})
        .catch((ex) => {
          var errorcode = ex.response.status;
          this.$router.push("/errorpage/" + errorcode);
        });
    },
    userupdate: function () {
      this.$router.push("/user/profile");
    },
    mylist: function () {
      this.$router.push("/blog/list");
    },
    logout: function () {
      this.$session.destroy();
      this.$router.go();
    },
    search: function () {
      axios
        .get("companyinfo/official", { params: { companyName: this.keyword } })
        .then((response) => {
          this.InfoOfficial = response.data;
          if (this.InfoOfficial.companyName != this.keyword) {
            alert("해당하는 기업 정보가 없습니다.");
            this.$router.push("/");
          } else {
            this.$router.push({
              path: "/blog/search",
              query: { keyword: this.keyword },
            });
          }
        })
        .catch((error) => {
          console.dir(error);
        });
    },
  },
  mount() {
    this.show();
  },
};
</script>

<style>
.search_main {
  background-color: #ecbef8;
}
.mainfront {
  background-color: #e3c4ff;
}
</style>
