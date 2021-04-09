<template>
  <div style="margin-top:5%; margin:5%;">
    <div class="mainew">
      <div class="head">
        <div class="headerobjectswrapper">
          <header>{{keyword}}</header>
        </div>
        <div class="subhead">
          <div v-if="this.$session.get('uid') == 'admin'" style="float:right">
            <div v-if="this.summary_update_ok == false">
              <div>
                <a href="#" @click="summary_update">수정</a>
              </div>
            </div>
          </div>

          <div v-if="InfoOfficial.summary == null && this.summary_update_ok == false"> <p></p></div>
          <div v-if="this.summary_update_ok == false"> {{InfoOfficial.summary}}</div>

          <div v-if="this.$session.get('uid') == 'admin'">
            <div v-if="this.summary_update_ok == true">
              <div style="float:right">
                <a href="#" @click="summary_update_done">완료</a>
              </div>
              <input type="text" v-model="summary_md_text" style="border: 1px solid #f0b400; width:80%; text-align:center;" />
            </div>
          </div>
        </div>
      </div>

      <div class="content" style="width:100%">
        <div class="collumns">
          <div class="collumn">
            <div class="head">
              <span class="headline hl3">INFORMATION</span>
              <p>
                <span class="headline hl4">
                  최종수정일자: {{InfoOfficial.lastModifiedDate}}
                </span>
              </p>
            </div>
            <div v-if="this.info_update_ok == false">
              <p>
                 <v-md-preview :text=  InfoOfficial.contents></v-md-preview>
              </p>
            </div>
            <div v-if="this.$session.get('uid') == 'admin'">
              <div v-if="this.info_update_ok == false">
                <p>
                  <button class="up" style="float:right;" @click="info_update">수정</button>
                </p>
              </div>
            </div>

            <div v-if="this.info_update_ok == true">
              <p>
                <textarea
                  v-model="info_md_text"
                  style="width:100%; height:150px; border:1px solid #f0b400; padding: 3px; border-radius: 3px;"
                ></textarea>
              </p>
              <p>
                <button class="up" style="float:right;" @click="info_update_done">완료</button>
              </p>
            </div>
          </div>
          <div class="collumn">
            <div class="head">
              <span class="headline hl3">OPEN DATA</span>
              <p>
                <span class="headline hl4">
                  최종수정자: {{ InfoUnofficial.lastModifiedUid }}
                  <br />
                  최종수정일자: {{ InfoUnofficial.lastModifiedDate }}
                </span>
              </p>
            </div>

            <div v-if="this.opendata_update_ok == false">
              <p>
                <v-md-preview :text= InfoUnofficial.contents></v-md-preview>
                </p>
            </div>

            <div v-if="this.$session.get('uid') != null">
              <div v-if="this.opendata_update_ok == false">
                <p>
                  <button class="up" style="float:right;" @click="opendata_update">수정</button>
                </p>
              </div>

              <div v-if="this.opendata_update_ok == true">
                <p>
                  <textarea
                    v-model="md_text"
                    style="width:100%; height:150px; border:1px solid #f0b400; padding: 3px; border-radius: 3px;"
                  ></textarea>
                </p>
                <p>
                  <button class="up" style="float:right;" @click="opendata_update_done">완료</button>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="tab">
      <div style="width:100%; height:50px; margin-bottom:180px">
      <h1 style="float:left; margin-left:40%; margin-top:auto;">Community</h1>
      <div v-if="this.$session.get('uid') != null">
      <button   style="margin-left: 10px; margin-top:57px;" class="fill" @click="writeBlog">글쓰기</button>
      </div>
      </div>
      <div class="tab-wrap">
        <input type="radio" name="tabs" id="tab1" checked />
        <div class="tab-label-content" id="tab1-content">
          <label for="tab1">All</label>
          <div class="tab-content">
           <div class="overflow-auto">
            <b-table
              id="my-table"
              :items="blogList"
              :per-page="perPage"
              :current-page="currentPage"
              :fields="fields"
              hover
              midium
              @row-clicked="todetail"
            >
            </b-table>
          </div>

            <b-pagination
              v-model="currentPage"
              pills
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
              align="center"
            ></b-pagination>
          </div>

        </div>

        <input type="radio" name="tabs" id="tab2" />
        <div class="tab-label-content" id="tab2-content">
          <label for="tab2">기업 정보</label>
          <div class="tab-content">
           <div class="overflow-auto">
            <b-table
              id="my-table"
              :items="blogList"
              :per-page="perPage"
              :current-page="currentPage"
              :fields="fields"
              midium
              hover
              @row-clicked="todetail"
              :filter="filter_info"
            >
            </b-table>
            <b-pagination
              v-model="currentPage"
              pills
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
              align="center"

            ></b-pagination>
          </div>
          </div>

        </div>

        <input type="radio" name="tabs" id="tab3" />
        <div class="tab-label-content" id="tab3-content">
          <label for="tab3">최신 뉴스</label>
          <div
            class="tab-content"
          >
           <div class="overflow-auto">
            <b-table
              id="my-table"
              :items="blogList"
              :per-page="perPage"
              :current-page="currentPage"
              :fields="fields"
              midium
              hover
              @row-clicked="todetail"
              :filter="filter_news"
            >
              <template v-slot:cell(title)="row">
                <a @click="moveDetail(row.item.bno)">{{row.item.title}}</a>
              </template>
            </b-table>

            <b-pagination
              v-model="currentPage"
              pills
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
              align="center"
            ></b-pagination>
          </div>
         </div>
        </div>

        <input type="radio" name="tabs" id="tab4" />
        <div class="tab-label-content" id="tab4-content">
          <label for="tab4">스터디 모집</label>
          <div
            class="tab-content"
          >
           <div class="overflow-auto">
            <b-table
              id="my-table"
              :items="blogList"
              :per-page="perPage"
              :current-page="currentPage"
              :fields="fields"
              midium
              hover
              @row-clicked="todetail"
              :filter="filter_etc"
            >
              <template v-slot:cell(title)="row">
                <a @click="moveDetail(row.item.bno)">{{row.item.title}}</a>
              </template>
            </b-table>
            <b-pagination
              v-model="currentPage"
              pills
              :total-rows="rows"
              :per-page="perPage"
              aria-controls="my-table"
              align="center"

            ></b-pagination>
          </div>
         </div>
        </div>
        <div class="slide"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/axios";

export default {
  name: "searchList",
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      keyword: "",
      blogList: {},
      InfoOfficial: {},
      InfoUnofficial: {},
      opendata_update_ok: false,
      md_text: "",
      info_update_ok: false,
      info_md_text: "",
      summary_update_ok: false,
      summary_md_text: "",
      filter_info:"기업정보",
      filter_etc:"스터디모집",
      filter_news:"최신뉴스",
      fields: [
        
         {
           key: "category",
           label: "카테고리"
         },
         {
           key: "title",
           label: "제목",
            sortable: true
         },
         {
           key: "uid",
           label: "닉네임"
         },
         {
           key: "views",
           label: "조회수",
            sortable: true,
         },
         {
           key: "likes",
           label: "좋아요",
            sortable: true,
         }
      ],
    };
  },
  computed: {
    rows() {
      return this.blogList.length
    }
  },
  mounted() {
    this.keyword = this.$route.query.keyword;

    axios
      .get("blog/search?keyword=" + this.keyword)
      .then((response) => {
        this.blogList = response.data;
      })
      .catch((error) => {
        var errorcode = ex.response.status;
        this.$router.push("/errorpage/" + errorcode);
      });

    axios
      .get("companyinfo/official", { params: { companyName: this.keyword } })
      .then((response) => {
        this.InfoOfficial = response.data;
        if (this.InfoOfficial.companyName != this.keyword) {
          alert("해당하는 기업 정보가 없습니다.");
          this.$router.push("/");
        }
      })
      .catch((error) => {
        console.dir(error);
      });

    axios
      .get("companyinfo/unofficial", { params: { companyName: this.keyword } })
      .then((response) => {
        this.InfoUnofficial = response.data;
      })
      .catch((error) => {
        console.dir(error);
      });
  },
  methods: {
    todetail(record, index){
      this.$router.push('/blog/detail/'+record.bno)
    },
    summary_update() {
      this.summary_update_ok = true;
      this.summary_md_text = this.InfoOfficial.summary;
    },
    summary_update_done() {
      this.summary_update_ok = false;
      this.InfoOfficial.summary = this.summary_md_text;
      this.summaryofficialModify();
    },
    opendata_update() {
      this.opendata_update_ok = true;
      this.md_text = this.InfoUnofficial.contents;
    },
    opendata_update_done() {
      this.opendata_update_ok = false;
      this.InfoUnofficial.contents = this.md_text;
      this.unofficialModify();
    },
    info_update() {
      this.info_update_ok = true;
      this.info_md_text = this.InfoOfficial.contents;
    },
    info_update_done() {
      this.info_update_ok = false;
      this.InfoOfficial.contents = this.info_md_text;
      this.officialModify();
    },
    summaryofficialModify() {
      this.InfoOfficial.currentUid = this.$session.get("uid");

      axios
        .put("companyinfo/summary", this.InfoOfficial)
        .then((response) => {
          this.$router.go(0);
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
    officialModify() {
      this.InfoOfficial.currentUid = this.$session.get("uid");

      axios
        .put("companyinfo/official", this.InfoOfficial)
        .then((response) => {
          this.$router.go(0);
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
    unofficialModify() {
      this.InfoUnofficial.currentUid = this.$session.get("uid");

      axios
        .put("companyinfo/unofficial", this.InfoUnofficial)
        .then((response) => {
          this.$router.go(0);
        })
        .catch((error) => {
          alert(error.response.data);
        });
    },
    moveDetail(bNo) {
      this.$router.push('/blog/detail/'+bNo);
    },
    writeBlog() {
      this.$router.push( {name:'BlogCreate', params:{"companyName": this.keyword}})
    }
  },
  create() {
    $(document).ready(function () {
      var numItems = $("li.fancyTab").length;

      if (numItems == 12) {
        $("li.fancyTab").width("8.3%");
      }
      if (numItems == 11) {
        $("li.fancyTab").width("9%");
      }
      if (numItems == 10) {
        $("li.fancyTab").width("10%");
      }
      if (numItems == 9) {
        $("li.fancyTab").width("11.1%");
      }
      if (numItems == 8) {
        $("li.fancyTab").width("12.5%");
      }
      if (numItems == 7) {
        $("li.fancyTab").width("14.2%");
      }
      if (numItems == 6) {
        $("li.fancyTab").width("16.666666666666667%");
      }
      if (numItems == 5) {
        $("li.fancyTab").width("20%");
      }
      if (numItems == 4) {
        $("li.fancyTab").width("25%");
      }
      if (numItems == 3) {
        $("li.fancyTab").width("33.3%");
      }
      if (numItems == 2) {
        $("li.fancyTab").width("50%");
      }
    });

    $(window).load(function () {
      $(".fancyTabs").each(function () {
        var highestBox = 0;
        $(".fancyTab a", this).each(function () {
          if ($(this).height() > highestBox) highestBox = $(this).height();
        });

        $(".fancyTab a", this).height(highestBox);
      });
    });
  },
};
</script>

<style>
.container {
  margin-top: 30px;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: "Source Sans Pro";
  font-weight: 700;
}

.fancyTab {
  text-align: center;
  padding: 15px 0;
  background-color: #eee;
  box-shadow: 0 0 0 1px #ddd;
  top: 15px;
  transition: top 0.2s;
}

.fancyTab.active {
  top: 0;
  transition: top 0.2s;
}

.whiteBlock {
  display: none;
}

.fancyTab.active .whiteBlock {
  display: block;
  height: 2px;
  bottom: -2px;
  background-color: #fff;
  width: 99%;
  position: absolute;
  z-index: 1;
}

.fancyTab a {
  font-family: "Source Sans Pro";
  font-size: 1.65em;
  font-weight: 300;
  transition: 0.2s;
  color: #333;
}

/*.fancyTab .hidden-xs {
  white-space:nowrap;
}*/

.fancyTabs {
  border-bottom: 2px solid #ddd;
  margin: 15px 0 0;
}

li.fancyTab a {
  padding-top: 15px;
  top: -15px;
  padding-bottom: 0;
}

li.fancyTab.active a {
  padding-top: inherit;
}

.fancyTab .fa {
  font-size: 40px;
  width: 100%;
  padding: 15px 0 5px;
  color: #666;
}

.fancyTab.active .fa {
  color: #cfb87c;
}

.fancyTab a:focus {
  outline: none;
}

.fancyTabContent {
  border-color: transparent;
  box-shadow: 0 -2px 0 -1px #fff, 0 0 0 1px #ddd;
  padding: 30px 15px 15px;
  position: relative;
  background-color: #fff;
}

.nav-tabs > li.fancyTab.active > a,
.nav-tabs > li.fancyTab.active > a:focus,
.nav-tabs > li.fancyTab.active > a:hover {
  border-width: 0;
}

.nav-tabs > li.fancyTab:hover {
  background-color: #f9f9f9;
  box-shadow: 0 0 0 1px #ddd;
}

.nav-tabs > li.fancyTab.active:hover {
  background-color: #fff;
  box-shadow: 1px 1px 0 1px #fff, 0 0px 0 1px #ddd, -1px 1px 0 0px #ddd inset;
}

.nav-tabs > li.fancyTab:hover a {
  border-color: transparent;
}

.nav.nav-tabs .fancyTab a[data-toggle="tab"] {
  background-color: transparent;
  border-bottom: 0;
}

.nav-tabs > li.fancyTab:hover a {
  border-right: 1px solid transparent;
}

.nav-tabs > li.fancyTab > a {
  margin-right: 0;
  border-top: 0;
  padding-bottom: 30px;
  margin-bottom: -30px;
}

.nav-tabs > li.fancyTab {
  margin-right: 0;
  margin-bottom: 0;
}

.nav-tabs > li.fancyTab:last-child a {
  border-right: 1px solid transparent;
}

.nav-tabs > li.fancyTab.active:last-child {
  border-right: 0px solid #ddd;
  box-shadow: 0px 2px 0 0px #fff, 0px 0px 0 1px #ddd;
}

.fancyTab:last-child {
  box-shadow: 0 0 0 1px #ddd;
}

.tabs .nav-tabs li.fancyTab.active a {
  box-shadow: none;
  top: 0;
}

.fancyTab.active {
  background: #fff;
  box-shadow: 1px 1px 0 1px #fff, 0 0px 0 1px #ddd, -1px 1px 0 0px #ddd inset;
  padding-bottom: 30px;
}

.arrow-down {
  display: none;
  width: 0;
  height: 0;
  border-left: 20px solid transparent;
  border-right: 20px solid transparent;
  border-top: 22px solid #ddd;
  position: absolute;
  top: -1px;
  left: calc(50% - 20px);
}

.arrow-down-inner {
  width: 0;
  height: 0;
  border-left: 18px solid transparent;
  border-right: 18px solid transparent;
  border-top: 12px solid #fff;
  position: absolute;
  top: -22px;
  left: -18px;
}

.fancyTab.active .arrow-down {
  display: block;
}

@media (max-width: 1200px) {
  .fancyTab .fa {
    font-size: 36px;
  }

  .fancyTab .hidden-xs {
    font-size: 22px;
  }
}

@media (max-width: 992px) {
  .fancyTab .fa {
    font-size: 33px;
  }

  .fancyTab .hidden-xs {
    font-size: 18px;
    font-weight: normal;
  }
}

@media (max-width: 768px) {
  .fancyTab > a {
    font-size: 18px;
  }

  .nav > li.fancyTab > a {
    padding: 15px 0;
    margin-bottom: inherit;
  }

  .fancyTab .fa {
    font-size: 30px;
  }

  .nav-tabs > li.fancyTab > a {
    border-right: 1px solid transparent;
    padding-bottom: 0;
  }

  .fancyTab.active .fa {
    color: #333;
  }
}
</style>