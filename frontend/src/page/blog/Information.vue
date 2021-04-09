<template>
  <div>
    <div class="container" style="margin-top:100px">
      <div>
   
    <!-- <div class="mt-3"><strong>{{ selected }}</strong></div> -->
  </div>
      
      <div class="form-group">
        <v-md-editor v-model="InfoOfficial.contents" height="400px"></v-md-editor>
      </div>
      
      <button @click="information"  class="fill" style="float:right;">작성완료</button>
    </div>
  </div>
</template>

<script>
import axios from "@/util/axios";

export default {
  name: "information",

  data: () => {
    return {
      InfoOfficial: {
        contents: "",
      
      },
      
      
    };
  },
  mounted() {},
  methods: {
    information() {
      this.blog.uid = this.$session.get("uid");
      console.log(this.blog);

      axios({
        method: "POST",
        url: "/blog/search",
        data: this.blog,
      })
        .then((Response) => {
          alert("포스팅이 완료되었습니다.");
          this.$router.go(-1);
        })
        .catch((ex) => {
          var errorcode = ex.response.status;
          this.$router.push("/errorpage/" + errorcode);
        });
    },
  },
};
</script>

<style>
.box-box {
  display: flex;
  width: 100%;
  height: calc(1.5em + 0.75rem + 2px);
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  border: none;
  box-shadow: 0 0.4px 0 0 gray;
}
.span-color {
  color: gray;
  font-size: 1.4rem;
}
input::placeholder {
  color: gray;
  font-style: oblique;
  font-size: 0.9rem;
}
.container {
  margin-top: 100px;
}
</style>