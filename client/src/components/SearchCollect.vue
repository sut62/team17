<template>
  <v-container>
    <v-card color="teal lighten-5">
    <v-row justify="center">
      <v-col cols="10">
            <v-btn @click="Back" style="background-color:SlateBlue; position: absolute; left: 15px;" dark>Back
              <v-icon dark right>mdi-arrow-left-bold</v-icon>
            </v-btn>
        </v-col> 
        <h1 class="display-1 font-italic" style="position: absolute; top: 20px; margin: 4px ; color: rgb(205, 123, 150) ;"
      >SEARCH COLLECT POINT</h1>
      <v-col cols="10" sm="2">
            <v-btn style="background-color: #000000 position: absolute; left: 35px;" @click="Logout" dark>LOG OUT
              <v-icon dark right>mdi-logout</v-icon>
            </v-btn>
        </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="4">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="CUSTOMER TEL"
                v-model="searchCollect.customerTel"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                
                <v-dialog v-model="dialog" persistent max-width="290" >
                  <template v-slot:activator="{ on }">
                    <v-btn v-on="on" @click="getCollectPoints" depressed large color="warning">Search
                      <v-icon dark right>mdi-magnify</v-icon>
                    </v-btn>
                  </template>
                <v-card>
                  <v-card-title class="headline">notification</v-card-title>
                    <v-card-text v-if="this.er == true" >This Customer Don't Have Point</v-card-text>
                    <v-card-text v-if="this.er == false" >Please Insert !!</v-card-text>
                      <v-card-actions><v-spacer></v-spacer>
                        <v-btn color="green darken-1" text @click="dialog = false">OK</v-btn>
                      </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>

    <v-row justify="center">
      <v-col  cols="8">
        <v-data-table :headers="headers" 
                      :items="items" 
                      :items-per-page="5" 
                      class="elevation-1">
        </v-data-table>
        <h1 class="display-1 font-italic" style="margin: 15px ; color: rgb(205, 123, 150) ;"
      >TOTAL POINT : {{total}}</h1>
      </v-col>
    </v-row>
     </v-card>
  </v-container>
</template>

<script>
import http from "../Api";

export default {
  name: "searchCollect",
  data() {
    return {
      searchCollect: {
        customerTel:""
      },
      headers: [
        { text: "CUSTOMER NAME", value: "customer.name" },
        { text: "CUSTOMER LAST NAME", value: "customer.lname" },
        { text: "COST TOTAL", value: "payment.price" },
        { text: "PRICE/POINT", value: "pointPrice.type" },
        { text: "POINT", value: "point" },
        { text: "CREATED BY", value: "employee.username" },
        { text: "DATE CREATE", value: "date" }
      ],
      total:0,
      er:null,
      dialog:false,
      items:[]
    };
  },

  methods: {
    /* eslint-disable no-console */
    Logout(){
      localStorage.setItem("id","");
      this.$router.push("/")
    },
    Back(){
      this.$router.push({name: 'CollectPoint'});
    },

    getCollectPoints() {
      this.total = 0;
      if(this.searchCollect.customerTel == ""){
        this.er = false;
    }else{
      
      http
        .get("/collectPointtel/" + this.searchCollect.customerTel)
        .then(response => {
          
          if(response.data == "" ){
             this.er = true;
            this.clear();
          }else{
           
            this.items = response.data;
            console.log(this.items);
            for(let i in this.items){
              this.total += this.items[i].point;
            }
            this.dialog = false;
          }
          })
        .catch(e => {
          console.log(e);
        });
    }
    },

    clear() {
      this.items = [];
      this.searchCollect.registerTel = "";
      this.total = 0;

    },

    refreshList() {
      this.getCollectPoints();
    }

    /* eslint-disable no-console */

  },

  mounted() {
    this.getCollectPoints();
  }

};

</script>