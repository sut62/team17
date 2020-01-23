<template>
  <v-container>
    <v-card color="teal lighten-5">
    <v-row justify="center">
      <h1 class="text mt-5 pt-5"
      style="text-align: center;
      font: 40px Times New Roman, sans-serif;
      width: 100%;"><strong>ADD EMPLOYEE SYSTEM</strong></h1>
    </v-row>
    <v-col></v-col>
    <v-row justify="center">
    <v-col cols="12" sm="5">
        <p class="font-weight-black">USERNAME</p>
        <v-text-field
          outlined 
          solo
          v-model="Employee.username"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-text-field>
    </v-col>
    <v-col cols="12" sm="5">
        <p class="font-weight-black">PASSWORD</p>
        <v-text-field 
          outlined
          solo
          label="8-16 characters"
          v-model="Employee.password"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-text-field>
    </v-col>
    </v-row>

    <v-row justify="center">
      <v-col id = "1" cols="12" sm="3">
        <p class="font-weight-black">Gender</p>
        <v-select
          label="select gender"
          outlined
          v-model="Employee.GenderId"
          :items="Genders"
          item-text="gender"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
      <v-col id = "2" cols="12" sm="3">
        <p class="font-weight-black">Type</p>
        <v-select
          label="select type"
          outlined
          v-model="Employee.TypeId "
          :items="Types"
          item-text="type"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
      <v-col id = "3" cols="12" sm="3">
        <p class="font-weight-black">Vacancy</p>
        <v-select
          label="select vacancy"
          outlined
          v-model="Employee.VacancyId"
          :items="Vacancys"
          item-text="vacancy"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-btn rounded style="margin: 10px ; background-color: #2D85D5" @click="Search" dark>SEARCH
        <v-icon dark right>mdi-account-search</v-icon>
      </v-btn>

      <v-dialog v-model="dialog" persistent max-width="290" >
        <template v-slot:activator="{ on }">
          <v-btn v-on="on" rounded style="margin: 10px ; background-color: #00C853" @click="saveEmployee" dark>CONFIRM
            <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
          </v-btn>
        </template>
          <v-card>
            <v-card-title class="headline">notification</v-card-title>
            <v-card-text v-if="suc" >Add Employee Success</v-card-text>
            <v-card-text v-if="!suc" >Fill out incomplete information or Password is invalid</v-card-text>
            <v-card-actions><v-spacer></v-spacer>
              <v-btn color="green darken-1" text @click="dialog = false">OK</v-btn>
            </v-card-actions>
          </v-card>  
      </v-dialog>

      <v-btn rounded style="margin: 10px ; background-color: #E53935" @click="clear" dark>CLEAR
        <v-icon dark right>mdi-cancel</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #000000" @click="Logout" dark>LOG OUT
        <v-icon dark right>mdi-logout</v-icon>
      </v-btn>
    </v-row>
    <v-col></v-col>
    </v-card>
  </v-container>
</template>

<script>
import http from "../Api";
export default {
  name: "Employee",
  data() {
    return {
      Employee: {
        username: "",
        password: "",
        GenderId: "",
        TypeId: "",
        VacancyId: ""
      },
      Genders: [],
      Types: [],
      Vacancys: [],
      dialog:false,
      suc:false
    };
  },

  methods: {
    /* eslint-disable no-console */
    Search(){
      this.$router.push("/SearchSale")
    },
    Logout(){
      this.$router.push("/")
    },

    getUsername() {
      http
        .get("/username")
        .then(response => {
          this.Username = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getPassword() {
      http
        .get("/password")
        .then(response => {
          this.Password = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getGenders() {
      http
        .get("/Gender")
        .then(response => {
          this.Genders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getTypes() {
      http
        .get("/Type")
        .then(response => {
          this.Types = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getVacancys() {
      http
        .get("/Vacancy")
        .then(response => {
          this.Vacancys = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่มบันทึกข้อมูล
    saveEmployee() {
      if (
        !this.Employee.username ||
        !this.Employee.password ||
        !this.Employee.GenderId ||
        !this.Employee.TypeId ||
        !this.Employee.VacancyId
      ) {
        this.suc = false;
        this.clear();
      } else {
        http
          .post(
            "/Employee/" +
              this.Employee.username +
              "/" +
              this.Employee.password +
              "/" +
              this.Employee.GenderId +
              "/" +
              this.Employee.TypeId +
              "/" +
              this.Employee.VacancyId,
            this.Employee
          )
          .then(response => {
            console.log(response);
            this.suc = true;
            this.clear();
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    // function เมื่อกดปุ่มล้างข้อมูล
    clear() {
      this.Employee.username = null;
      this.Employee.password = null;
      this.Employee.GenderId = null;
      this.Employee.TypeId = null;
      this.Employee.VacancyId = null;
    }
  },
  mounted() {
    this.getUsername();
    this.getPassword();
    this.getGenders();
    this.getTypes();
    this.getVacancys();
  }
};
</script>

