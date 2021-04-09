<template>
  <div>
    <div class="editprofile">
      <!------ Include the above in your HEAD tag ---------->

      <div class="container emp-profile">
          <div class="row">
            <div class="col-md-4">
              <div class="profile-img">
                <v-img
                v-if="imageUrl"
                :src="imageUrl"
                alt
                width="248px"
                height="200px"
                style="left:53px;"
              ></v-img>
              </div>
            </div>
            <div class="col-md-6">
              <div class="profile-head">
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                  <li class="nav-item">
                    <a class="nav-link active" id="home-tab">About</a>
                  </li>
                </ul>
              </div>
              <div class="tab-content profile-tab" id="myTabContent">
                <div
                  class="tab-pane fade show active"
                  id="home"
                  role="tabpanel"
                  aria-labelledby="home-tab"
                >
                  <div class="row">
                    <div class="col-md-6">
                      <label>User Id</label>
                    </div>
                    <div class="col-md-6">
                      <p>{{user.uid}}</p>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-6">
                      <label>Email</label>
                    </div>
                    <div class="col-md-6">
                      <p>{{user.email}}</p>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-6">
                      <label>자기소개</label>
                    </div>
                    
                    <div class="col-md-6">
                      <p>
                      <pre style="width: 300px; height:150px; white-space:normal;" >{{ user.introduce }}</pre>
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-md-2">
              <input
                type="submit"
                @click="Edit"
                class="profile-edit-btn"
                name="btnAddMore"
                value="Edit Profile"
              />
            </div>
          </div>
          <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-8"></div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/util/axios";

export default {
  name: "SignupRequest",
  data() {
    return {
      user: {},
      password: "",
      introduce: "",
      passwordcon: "",
      imageUrl: "",
    };
  },
  methods: {
    Edit: function () {
      this.$router.push("/user/update");
    },
  },
  mounted() {
    var uid = this.$session.get("uid");
    axios
      .post("/user/update", uid)
      .then((Response) => {
        this.user = Response.data;
        this.imageUrl = 'http://i3c201.p.ssafy.io/api/img?profileImgName=' + this.user.image;
      })
      .catch((ex) => {
        var errorcode = ex.response.status;
        this.$router.push("/errorpage/" + errorcode);
      });
  },
};
</script>
