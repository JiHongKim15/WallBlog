<template>
  <div class="container" style="margin-top: 5%">
    <div>
      <h1 style="font-size:40px;">기업 목록</h1>
      <br>
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
        </tr>
        <tr v-for="company in companyList">
          <td v-if="company.official == true" rowspan="2">
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
          <td v-if="company.official">INFORMATION</td>
          <td v-else>OPENDATA</td>
        </tr>
      </table>
    </div>
  </div>

</template>

<script>
import axios from "@/util/axios";

export default {
  name: "companyList",
  data() {
    return {
      searchCompanyName: '',
      companyList: {}
    }
  },
  mounted() {
    this.searchCompanyList();
  },
  methods: {
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