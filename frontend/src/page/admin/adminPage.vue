<template>
  <div class="container" style="margin-top: 5%">
    <h2>관리자 페이지</h2>

    <br>
    <div>
      <h3>새 기업 추가</h3>
      <b-input-group prepend="기업명" class="mt-3">
        <b-form-input v-model="newCompanyName" @keyup.enter="newCompanyPage"></b-form-input>
        <b-input-group-append>
          <b-button variant="outline-primary" @click="newCompanyPage">추가</b-button>
        </b-input-group-append>
      </b-input-group>
    </div>

    <br>

    <div>
      <h3>기업 목록</h3>
      <b-input-group prepend="기업명" class="mt-2 mb-2">
        <b-form-input v-model="searchCompanyName" @keyup.enter="searchCompanyList"></b-form-input>
        <b-input-group-append>
          <b-button variant="outline-primary" @click="searchCompanyList">검색</b-button>
        </b-input-group-append>
      </b-input-group>
      <table width="100%">
        <tr>
          <th>
            기업명
          </th>
          <th>
            생성 일자
          </th>
          <th>
            마지막 수정 아이디
          </th>
          <th>
            마지막 수정일자
          </th>
          <th>
            분류
          </th>
          <th>
            삭제
          </th>
        </tr>
        <tr v-for="company in companyList">
          <td v-if="company.official" rowspan="2">
            <a @click="companyPage(company.companyName)"><font
                style="color: #0645AD; text-decoration: underline">{{ company.companyName }}</font></a>
          </td>
          <td v-if="company.official" rowspan="2">
            {{ company.createdDate }}
          </td>
          <td>
            {{ company.lastModifiedUid }}
          </td>
          <td>
            {{ company.lastModifiedDate }}
          </td>
          <td v-if="company.official">공식</td>
          <td v-else>비공식</td>
          <td v-if="company.official" rowspan="2" style="vertical-align: center">
            <b-button variant="danger" @click="deleteCompanyPage(company.companyName)" size="sm">삭제</b-button>
          </td>
        </tr>
      </table>
    </div>

    <br>
    <div>
      <h3>회원 관리</h3>
      <b-input-group prepend="유저 아이디" class="mt-2 mb-2">
        <b-form-input v-model="uid" @keyup.enter="searchUserList"></b-form-input>
        <b-input-group-append>
          <b-button variant="outline-primary" @click="searchUserList">검색</b-button>
        </b-input-group-append>
      </b-input-group>
      <table width="100%">
        <tr>
          <th>
            아이디
          </th>
          <th>
            이메일
          </th>
          <th>
            생성일
          </th>
          <th>
            최근수정문서
          </th>
        </tr>
        <tr v-for="user in userList">
          <td>
            <a @click="searchBlogList(user.uid)"><font style="color: #0645AD; text-decoration: underline">{{
                user.uid
              }}</font></a>
          </td>
          <td>
            {{ user.email }}
          </td>
          <td>
            {{ user.create_date }}
          </td>
          <td v-if="user.lastModify != null">
            <a @click="companyPage(user.lastModify)"><font
                style="color: #0645AD; text-decoration: underline">{{ user.lastModify }}</font></a>
          </td>
          <td v-else>없음</td>
        </tr>
      </table>
    </div>

    <br>
    <div>
      <h3>↑ 해당 유저의 블로그 작성글</h3>
      <table width="100%">
        <tr>
          <th>
            기업명
          </th>
          <th>
            제목
          </th>
          <th>
            아이디
          </th>
          <th>
            좋아요
          </th>
          <th>
            방문수
          </th>
          <th>
            생성일자
          </th>
        </tr>
        <tr v-for="blog in blogList">
          <td>
            <a @click="companyPage(blog.keyword)"><font
                style="color: #0645AD; text-decoration: underline">{{ blog.keyword }}</font></a>
          </td>
          <td>
            <a @click="moveBlogPage(blog.bno)"><font style="color: #0645AD; text-decoration: underline">{{
                blog.title
              }}</font></a>
          </td>
          <td>
            {{ blog.uid }}
          </td>
          <td>
            {{ blog.likes }}
          </td>
          <td>
            {{ blog.views }}
          </td>
          <td>
            {{ blog.create_date }}
          </td>
        </tr>
      </table>
    </div>

    <br>
    <div>
      <h3>블로그 카테고리 목록</h3>
      <b-input-group prepend="카테고리명" class="mt-2 mb-2">
        <b-form-input v-model="newCategory" @keyup.enter="createCategory"></b-form-input>
        <b-input-group-append>
          <b-button variant="outline-primary" @click="createCategory">추가</b-button>
        </b-input-group-append>
      </b-input-group>
      <table width="100%">
        <tr>
          <th>
            카테고리
          </th>
          <th>
            삭제
          </th>
        </tr>
        <tr v-for="category in categoryList">
          <td>{{ category }}</td>
          <td>
            <b-button variant="danger" @click="deleteCategory(category)" size="sm">삭제</b-button>
          </td>
        </tr>
      </table>
    </div>

  </div>

</template>

<script>
import axios from "@/util/axios";


export default {
  name: "adminPage",
  data() {
    return {
      newCompanyName: '',
      searchCompanyName: '',
      companyList: {},
      userList: {},
      uid: '',
      currentId: "",
      blogList: {},
      categoryList: {},
      newCategory: '',
    }
  },
  created() {
    if (!this.isAdmin(this.$session.get("uid"))) {
      this.$router.go(-1);
    }
  },
  mounted() {
    this.searchCompanyList();
    this.searchUserList();
    this.searchCategoryList();
  },
  methods: {
    newCompanyPage() {
      if (this.newCompanyName != "") {
        axios
            .post('/admin/company', {"companyName": this.newCompanyName, "uid": this.$session.get("uid")})
            .then((response) => {
              alert(this.newCompanyName + "의 기업 페이지 생성 성공")
              this.newCompanyName = '';
              this.searchCompanyList();
              this.searchUserList();
            })
            .catch((error) => {
              alert(error.response.data);
            })
      } else {
        alert("기업명을 입력해주세요.");
      }
    },
    deleteCompanyPage(deleteCompanyName) {
      axios
          .delete('/admin/company', {
            data:
                {"companyName": deleteCompanyName, "uid": this.$session.get("uid")}
          })
          .then((response) => {
            alert(deleteCompanyName + "의 기업 페이지 삭제 성공")
            this.searchCompanyList();
            this.searchUserList();
          })
          .catch((error) => {
            alert(error.response.data);
          })
    },
    companyPage(companyName) {
      this.$router.push('/blog/search?keyword=' + companyName);
    },

    searchCompanyList() {
      let query = '';
      if (this.searchCompanyName != '') {
        query = '?companyName=' + this.searchCompanyName;
      }
      axios
          .get('/admin/company' + query)
          .then((response) => {
            this.companyList = response.data;
          })
          .catch((error) => {
            alert(error.response.data);
          })
    },
    searchUserList() {
      let query = '';
      if (this.uid != '')
        query = '?uid=' + this.uid;

      axios
          .get('/admin/user' + query)
          .then((response) => {
            this.userList = response.data;
          })
          .catch((error) => {
            alert(error.response.data);
          })
    },
    searchBlogList(uid) {
      let query = '?uid=' + uid;
      axios
          .get('/admin/blog' + query)
          .then((response) => {
            this.blogList = response.data;
          })
          .catch((error) => {
            alert(error.response.data);
          })
    },
    moveBlogPage(bNo) {
      this.$router.push('/blog/detail/' + bNo);
    },
    isAdmin(uid) {
      if (uid != 'admin')
        return false;
      return true;
    },
    searchCategoryList() {
      axios
          .get('/admin/category')
          .then((response) => {
            console.dir(response);
            this.categoryList = response.data;
          })
          .catch((error) => {
            alert(error.response.data);
          })
    },
    createCategory() {
      axios
          .post('/admin/category', {"category": this.newCategory, "uid": this.$session.get("uid")})
          .then((response) => {
            alert("생성 성공");
            this.newCategory = '';
            this.searchCategoryList();
          })
          .catch((error) => {
            alert(error.response.data);
          })
    },
    deleteCategory(category) {
      axios
          .delete('/admin/category', {data: {"category": category, "uid": this.$session.get("uid")}})
          .then((response) => {
            alert("삭제 성공");
            this.searchCategoryList();
          })
          .catch((error) => {
            console.dir(error);
            alert(error.response.data);
          })
    }
  }
}
</script>

<style scoped>
tr, th, td {
  border: 1px solid;
  border-collapse: collapse;
  text-align: center;
}
</style>