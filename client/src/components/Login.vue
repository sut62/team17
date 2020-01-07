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
          <v-row justify="center">
             <v-col class="ml-10" cols="12" sm="6" md="6">
            <v-select class=" px-12 mx-12"
              label="Choose Vacancy"
              outlined
              v-model="login.choose"
              :items="chooses"
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-select>
            </v-col>
          </v-row>
          <v-row justify="center" class="pb-12 mb-10" >
            <v-btn @click="Login" class="cyan lighten-1 white--text">Login</v-btn>
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
          password: "",
          choose:""
          },
          employee:[],
          chooses:["Manager","Employee"]
        };
    },

 methods: {
  Login() {
  if(!this.login.username||!this.login.password||!this.login.choose){
  alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
  }
  else if(this.login.choose == "Employee"){
     http
        .get("/Employee/" + this.login.username + "/" + this.login.password)
        .then(response => {
          
          if (this.login.username == response.data.username && this.login.password == response.data.password) {
            this.employee = response.data;
            this.login.username = response.data.username;
            this.login.password = response.data.password;
            alert(this.login.username)
            alert("Login complete!");
            this.$router.push({name: 'Dashboard' , params: {em: this.employee.id} }); 
          } 
          else {
            alert("Incorrect Username or Password");
          }
          })
  }else if (this.login.choose == "Manager"){
    http
    .get("/Employee/" + this.login.username  + "/" + this.login.password)
    .then(response=>{

       if (this.login.username == response.data.username && this.login.password == response.data.password) {
            this.employee = response.data;
            this.login.username = response.data.username;
            this.login.password = response.data.password;
            alert(this.login.username)
            alert("Login complete!");
            this.$router.push({name: 'Employee' , params: {em: this.employee.id} }); 
          } 
          else {
            alert("Incorrect Username or Password");
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