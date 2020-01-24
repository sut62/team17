<template>
  <v-container>
    <v-card color="teal lighten-5">
    <v-row justify="center">
      <v-row justify="center">
      <v-col cols="10">
            <v-btn @click="Back" color="#584656" style=" position: absolute; left: 20px;" dark>
              <v-icon dark >mdi-arrow-left-bold</v-icon>
                Back
            </v-btn>
        </v-col>
      </v-row>
      <h1 class="display-2" style="position: absolute; top: 20px; color: #3b4255 ;"
      ><strong>PRE-ORDER LIST</strong></h1>
    </v-row>
    <v-col></v-col>

    <v-card>
      
        <v-data-table 
            :headers="headers"
            :items="items"
            :items-per-page="10"
            class="elevation-1 mt-9"
            loading-text="No Pre-Order list"
          >
         </v-data-table>
      
      </v-card>
    </v-card>
  </v-container>
</template>

<script>
import http from "../Api";

export default {
  name: "Pre_Order",
  data() {
    return {
      headers: [
          {
            text: 'ID',
            align: 'left',
            sortable: false,
            value: 'id',
          },
          { text: 'Customer', value: 'cus_name' },
          { text: 'Tel', value: 'tel' },
          { text: 'Brand', value: 'brand.name' },
          { text: 'Type', value: 'type_product.name' },
          { text: 'Quantity', value: 'quantity' },
          { text: 'Date', value: 'date' },
        ],
        items:[]
    }
  },
  methods: {


    /* eslint-disable no-console */
    Back(){
      this.$router.push("/PreOrder")
    },
    getPre_Order() {
      http
        .get("/pre_order")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
   
   /* eslint-enable no-console */
  },
  mounted() {
    this.getPre_Order();
  }
}
</script>