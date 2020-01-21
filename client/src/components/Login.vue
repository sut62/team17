<template>
    <v-container>
        <div class="LoginPage" >
         <v-card>
          <h1 class="text mt-5 pt-5"><strong>Login</strong></h1>
          <v-form>
          <v-row justify="center">
              <v-col class="ml-12" cols="12" sm="6" md="6">
              <v-text-field label="Username" 
              v-model="login.username"
              :rules="[(v) => !!v || 'Item is required']" 
              required>
              </v-text-field>
              </v-col>
          </v-row>
          <v-row justify="center">
               <v-col class="ml-10" cols="12" sm="6" md="6">
              <v-text-field label="Password"  
              :type="'password'"
              v-model="login.password" 
              :rules="[(v) => !!v || 'Item is required']" 
              required></v-text-field>
              </v-col>
          </v-row>
          <v-row justify="center" class="pb-12 mb-10" >
            <v-dialog v-model="dialog" persistent max-width="290" >
              <template v-slot:activator="{ on }">
                <v-btn v-on="on" @click="Login" class="cyan lighten-1 white--text">Login</v-btn>
              </template>
            <v-card>
              <v-card-title class="headline">notification</v-card-title>
                <v-card-text v-if="er" >Incorrect Username or Password !!</v-card-text>
                <v-card-text v-if="!er" >Please Insert All !!</v-card-text>
                  <v-card-actions><v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="dialog = false">OK</v-btn>
                  </v-card-actions>
            </v-card>
            </v-dialog>
          </v-row>
          </v-form>
        </v-card>
        </div>
    </v-container>
</template>

<script>
import http from "../Api";
export default {
  name: "Login",
    data(){
        return{
          login:{
          username: "",
          password: ""
          },
          employee:[],
          er: null,
          dialog: false
        };
    },

 methods: {
  Login() {
  if(!this.login.username||!this.login.password){
    this.er = false;
  }else{
  http
        .get("/Employee/" + this.login.username + "/" + this.login.password)
        .then(response => {
    if (this.login.username == response.data.username && this.login.password == response.data.password) {
      if(response.data.vacancy.vacancy == "Employee"){
            this.employee = response.data;
            this.login.username = response.data.username;
            this.login.password = response.data.password;
            this.$router.push({name: 'Dashboard'}); 
            localStorage.setItem("id",this.employee.id);
      }else if(response.data.vacancy.vacancy == "Manager"){
      this.employee = response.data;
            this.login.username = response.data.username;
            this.login.password = response.data.password;
            this.$router.push({name: 'Employee'});
            localStorage.setItem("id",this.employee.id);}
      this.dialog = false;
    }else{
      this.er = true;
    }
        })
  }
  }
 }
};
</script>

<style scoped>
div.LoginPage {
  margin: 50px, 20px;
  padding: 20px, 50px;
}
span {
  font: 20px BankGothic Md BT, sans-serif;
  -width: 100%;
}

h1.text {
  text-align: center;
  font: 30px BankGothic Md BT, sans-serif;
  width: 100%;
}
</style>>