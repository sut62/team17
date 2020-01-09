<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="4">
        <p>Employee</p>

        <v-select
          class="my-2"
          outlined
          v-model="add_product.employeeId"
          :items="employees"
          label="Employee"
          item-text="username"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12" sm="4">
        <p>Brand</p>

        <v-select
          class="my-2"
          outlined
          v-model="add_product.brandId"
          :items="brands"
          label="Brand"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Type Product</p>

        <v-select
          class="my-2"
          outlined
          v-model="add_product.type_productId"
          :items="type_products"
          label="Type Product"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12" sm="4">
        <p>Quantity</p>

        <v-text-field
            
            solo
            outlined
            v-model="add_product.quantity"
            label="Quantity"
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Price</p>
          <v-text-field
            solo
            outlined
            v-model="add_product.price"
            label="Price"
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
        </v-col>
    </v-row>

    <div class="my-2">
            <v-btn @click="saveadd_product" :class="{ grey: !valid, green: valid }">Add Product</v-btn>
    </div>
    <div class="my-2">
            <v-btn @click="clear" :class="{ Gray: valid }">Clear</v-btn>
    </div>
    
  </v-container>
</template>

<script>
import http from "../Api";
export default {
  name: "add_product",
  data() {
    return {
      add_product: {
        employeeId: "",
        brandId: "",
        type_productId: "",
        quantity: "",
        price: ""
      },
      employees: [],
      brands: [],
      type_products: [] 
      
    };
  },
  methods: {
    /* eslint-disable no-console */
    getEmployee() {
      http
        .get("/Employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getBrand() {
      http
        .get("/brand")
        .then(response => {
          this.brands = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getType_Product() {
      http
        .get("/type_Product")
        .then(response => {
          this.type_products = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
   
    // function เมื่อกดปุ่มบันทึกข้อมูล
    saveadd_product() {
      if (
        !this.add_product.employeeId ||
        !this.add_product.brandId ||
        !this.add_product.type_productId ||
        !this.add_product.quantity ||
        !this.add_product.price
      ) {
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
      } else {
        http
          .post(
            "/add_product/" +
              this.add_product.employeeId +
              "/" +
              this.add_product.brandId +
              "/" +
              this.add_product.type_productId +
              "/" +
              this.add_product.quantity +
              "/" +
              this.add_product.price,
            this.add_product
          )
          .then(response => {
            alert("Succeed");
            console.log(response);
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    // function เมื่อกดปุ่มล้างข้อมูล
    clear() {
      this.add_product.employeeId = null;
      this.add_product.brandId = null;
      this.add_product.type_productId = null;
      this.add_product.quantity = null;
      this.add_product.price = null;
  },
    refreshList() {
    this.getEmployee();
    this.getBrand();
    this.getType_Product();
    }
   
   /* eslint-enable no-console */
  },
  
  mounted() {
    this.getEmployee();
    this.getBrand();
    this.getType_Product();
  }
};
</script>