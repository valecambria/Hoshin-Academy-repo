

const app = Vue.createApp({
  data() {
    return {
      email: "",
      contraseña: "",
      primerNombre: "",
      apellido: "",
    };
  },

  created() {},
  methods: {
    ingreso() {
      axios
        .post("/api/login", `email=${this.email}&contraseña=${this.contraseña}`, {
        })
        .then((response) =>
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Ingreso correctamente",
            showConfirmButton: false,
            timer: 1500,
          })
          )
        .then(() => {
          if(this.email.includes("@admin.com")){
            window.location.href = "/pagoYkyc.html";
          }else{
            window.location.href = "/pagoYkyc.html";
          }
        })
        .catch((response) =>
          Swal.fire({
            confirmButtonColor: '#5d82a7',
            icon: "error",
            title: "Oops...",
            text: "El email o la contraseña estan mal , intente de nuevo.",
          })
        );
    },

    salida() {
      axios
        .post("/api/logout")
        .then((response) => console.log("signed out!!!"));
    },

    registro() {
      axios
        .post(
          "/api/clientes",
          `primerNombre=${this.primerNombre}&apellido=${this.apellido}&email=${this.email}&contraseña=${this.contraseña}`
        )
        
        .then((response) =>
          axios.post("/api/login", `email=${this.email}&contraseña=${this.contraseña}`)
        )
        .then((response) =>
          Swal.fire({
            position: "top-end",
            icon: "success",
            title: "Bienvenido a Hoshin Academy!",
            showConfirmButton: false,
            timer: 1500,
          })
          )
        .then((x) => (location.href = "/pagoYkyc.html"))
        .then((response) => console.log("registrado!!!"))
        .catch((response) =>
          Swal.fire({
            confirmButtonColor: '#5d82a7',
            icon: "error",
            title: "Oops...",
            text: "Este email ya ha sido registrado!",
                    })
        );
    },
  },
}).mount("#app");
