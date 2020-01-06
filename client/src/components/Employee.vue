<template>
  <v-container>
    <v-card color="teal lighten-5">
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
          v-model="Employee.password"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-text-field>
    </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="12" sm="3">
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
        ></v-select> <!-- v-model=bind combobox กับ variable ทำการ initialize variable ใน data property -->
      </v-col>
      <v-col cols="12" sm="3">
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
      <v-col cols="12" sm="3">
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
      <v-btn rounded style="margin: 10px ; background-color: #00C853" @click="saveEmployee" dark>CONFIRM
        <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #E53935" @click="clear" dark>CLEAR
        <v-icon dark right>mdi-cancel</v-icon>
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
      Vacancys: []
    };
  },

  methods: {
    /* eslint-disable no-console */
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
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
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
            alert("บันทึกข้อมูลสำเร็จ!");
            console.log(response);
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