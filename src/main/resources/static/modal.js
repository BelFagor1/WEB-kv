document.querySelectorAll(".delete-btnSpec").forEach((button) => {
    button.addEventListener("click", function () {
        const fileId = this.id.split("-")[1]; 
        const form = document.getElementById(`myForm-${fileId}`); 

       
        const modal = document.getElementById("confirmModal");
        modal.style.display = "flex";

        
        document.getElementById("confirmBtn").onclick = function () {
            modal.style.display = "none";
            form.submit(); 
        };

     
        document.getElementById("cancelBtn").onclick = function () {
            modal.style.display = "none";
        };
    });
});


window.addEventListener("click", (event) => {
    const modal = document.getElementById("confirmModal");
    if (event.target === modal) {
        modal.style.display = "none";
    }
});
