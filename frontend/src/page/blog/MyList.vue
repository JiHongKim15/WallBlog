<template>
  <div>
    <div style="text-align:center; margin-top: 10%;">
      <ul id="compositions-list" class="pure-tree main-tree">
        <li v-for="(kw, index) in re.keyword" :key="index">
          <label for="trigger-views">{{kw}}</label>
          <div v-for="(list, index) in re.lists" :key="index" v-if="kw === list.keyword">
            <router-link :to="'/blog/detail/'+list.bno">
              <li class="pure-tree_link">{{list.title}}</li>
            </router-link>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "@/util/axios";

export default {
  name: "mylist",
  data() {
    return {
      re: {},
    };
  },
  methods: {
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
  mounted() {
    var uid = this.$session.get("uid");
    axios
      .post("/blog/list", uid)
      .then((Response) => {
        this.re = Response.data;
      })
      .catch((Response) => {
        var errorcode = ex.response.status;
        this.$router.push("/errorpage/" + errorcode);
      });
  },
};
</script>

<style>
</style>