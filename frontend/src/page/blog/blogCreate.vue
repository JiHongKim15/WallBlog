<template>
  <div>
    <div class="container" style="margin-top:100px">
      <div>
        <!-- <div class="mt-3"><strong>{{ selected }}</strong></div> -->
      </div>
      <div class="form-group">
        <div class="form-group">
          <label for="exampleFormControlInput1" class="ml-2 span-color">기업명</label>
         

          <input
            v-model="blog.keyword"
            type="email"
            class="box-box"
            id="exampleFormControlInput1"
            placeholder="기업명을 입력해주세요"
          />
        </div>
        <label for="exampleFormControlInput1" class="ml-2 span-color">Title</label>
        <br />
        <div style="width:100%">
          <b-form-select
            v-model="blog.category"
            :options="options"
            class="mb-3"
            value-field="item"
            text-field="name"
            disabled-field="notEnabled"
            style="width:130px; float: left; margin-right:14px"
          ></b-form-select>
          <input
            v-model="blog.title"
            type="email"
            class="box-box"
            id="exampleFormControlInput1"
            placeholder="제목을 입력해주세요."
            style="width:87.5%"
          />
        </div>
      </div>

      <div class="form-group">
        <v-md-editor
          v-model="blog.contents"
          height="400px"
          style="box-shadow: 0.4px 0.4px 0.4px 0.4px #808080;"
        ></v-md-editor>
      </div>

      <button @click="createblog" class="fill" style="float:right;">작성완료</button>
    </div>
  </div>
</template>

<script>
import axios from "@/util/axios";

export default {
  name: "BlogCreate",

  data: () => {
    return {
      blog: {
        contents: "",
        keyword: "",
        title: "",
        uid: "",
        category: "",
      },
      options: [],
      options_key:[],
      companyList: {},
    };
  },
  mounted() {
    
    axios
      .get("/admin/category")
      .then((response) => {
        this.options = response.data;
        this.blog.category = response.data[0];
      })
      .catch((error) => {
        alert(error.response.data);
      });

    this.blog.keyword = this.$route.params.companyName;
  },
  methods: {
    createblog() {
      this.blog.uid = this.$session.get("uid");
      console.log(this.blog);

      axios({
        method: "POST",
        url: "/blog",
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
  box-shadow: 0 0.4px 0 0 #808080;
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