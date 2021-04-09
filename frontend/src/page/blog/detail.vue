<template>
  <div style="margin:1px">
    <div class="blog">
     

      <div class="blog-container">
        <div class="blog-header">
          <div class="blog-cover">
            <div class="blog-author">
              <v-img
                v-if="imageUrl"
                :src="imageUrl"
                style="size: cover;
        radius: 50%;
        height: 32px;
        position: relative;
        top: 8px;
        width: 32px;
        float:left; margin-right:-30px;"
              ></v-img>

              <h3>{{blog.uid}}</h3>
            </div>
          </div>
        </div>

        <div class="blog-body">
          <div class="blog-title">
            <div style="margin: 0px;text-align: center;color: black;"> 
              <a href="#" style="font-size:30px; width:100%;  color:black;">{{blog.title}}</a>
            </div>
          </div>
          <div class="blog-summary">
            <v-md-preview :text="blog.contents"></v-md-preview>
          </div>
          <div class="blog-tags">
            <ul>
              <!-- keyword만 태그로 -->
              <li>
                <a href="#">{{blog.keyword}}</a>
              </li>
              <li>
                <a href="#">{{blog.category}}</a>
              </li>
            </ul>
          </div>
        </div>

        <div class="blog-footer">
          <ul>
            <!-- 작성시간 입력하기 -->
            <li style="top:18px;">{{blog.create_date}}</li>
            <li class="views" style="top:18px; left:320px;">
              <a href="#">
                <v-icon>mdi-eye-outline</v-icon>
                {{blog.views}}
              </a>
            </li>
            <li class="comments" style="top: -5px; left:26%;">
              <a href="#">
                <v-icon>mdi-comment-outline</v-icon>
                {{this.comment_list.length}}
              </a>
            </li>
            <li class="shares" style="top:20px;" @click="likesup">
              <a href="#">
                <v-icon>mdi-thumb-up-outline</v-icon>
                {{blog.likes}}
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div class="comments">
      <div v-if="this.$session.get('uid') != null">
      <p>댓글작성</p>
      <div class="input-group-prepend">
        <input
          type="text"
          placeholder="Comment"
          style="boder: none; border-bottom: 1px solid #bdb9b1; width:100%; "
          aria-label="Username"
          aria-describedby="addon-wrapping"
          v-model="comment.contents"
        />
        <button type="button" class="raise" @click="createComment">작성</button>
      </div>
      </div>
      <div class="comment" v-for="(comments, idx) in comment_list" :key="idx">
        <div>
          <p>
            <strong>{{ comments.uid }}</strong>
            - {{comments.regdate}}
          </p>
          <span>{{ comments.contents }}</span>
          <br />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/axios";

export default {
  name: "detail",
  data() {
    return {
      blog: {},
      comment_list: {},
      comment: {
        bno: 0,
        uid: "",
        contents: "",
      },
      commentcnt: "",
      imageUrl: "",
    };
  },
  mounted() {
    axios
      .get("/blog/detail/" + this.$route.params.bno)
      .then((response) => {
        this.blog = response.data;

        axios
          .get("/user/uid?uid=" + this.blog.uid)
          .then((res) => {
            console.log(res.data);
            this.imageUrl =
              "http://i3c201.p.ssafy.io/api/img?profileImgName=" + res.data.image;
          })
          .catch((error) => {
            var errorcode = ex.response.status;
            this.$router.push("/errorpage/" + errorcode);
          });
      })
      .catch((error) => {
        var errorcode = ex.response.status;
        this.$router.push("/errorpage/" + errorcode);
      });
    axios.get("comments/all/" + this.$route.params.bno).then((response) => {
      this.comment_list = response.data;
    });
  },
  methods: {
    likesup() {
      axios({
        method: "GET",
        url: "blog/detail/" + this.$route.params.bno + "/like",
      })
        .then((Re) => {
          this.$router.go(0);
        })
        .catch((error) => {
          var errorcode = ex.response.status;
          this.$router.push("/errorpage/" + errorcode);
        });
    },
    createComment() {
      this.comment.uid = this.$session.get("uid");
      this.comment.bno = this.$route.params.bno;
      console.log(this.comment.contents);

      axios({
        method: "POST",
        url: "/comments",
        data: {
          uid: this.comment.uid,
          bno: this.comment.bno,
          contents: this.comment.contents,
        },
      })
        .then((Re) => {
          console.log(this.comment.bno);
          this.$router.go();
        })
        .catch((error) => {
          var errorcode = ex.response.status;
          this.$router.push("/errorpage/" + errorcode);
        });
    },
  },
};
</script>

<style scoped>
.content-detail-content-info {
  border: 1px solid black;
  display: flex;
  justify-content: space-between;
}

.content-detail-content-info-left {
  width: 720px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
}

.content-detail-content-info-right {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem;
}

.content-detail-content {
  border: 1px solid black;
  margin-top: 1rem;
  padding-top: 1rem;
  min-height: 720px;
}

.content-detail-button {
  border: 1px solid black;
  margin-top: 1rem;
  padding: 2rem;
}
</style>