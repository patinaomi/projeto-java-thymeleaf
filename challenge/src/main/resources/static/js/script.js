window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    const erro = urlParams.get("erro");
    if (erro) {
        alert(erro);
    }

    const modaisComTempo = {
        resumoModal: 8000,
        createdFeedbackModal: 4000,
        deletedFeedbackModal: 4000,
        emptyFeedbackModal: 4000,
        deletedFeedbackErrorModal: 4000,
        editedFeedbackModal: 4000,
        updatedClinicaModal: 4000,
        statusUpdatedModal: 4000,
        deletedDentistModal: 4000,
        editedDentistModal: 4000,
        emptyDentistModal: 4000,
        logoutModal: 3000,
        errorModal: 3000,
        emptyConsultaModal: 4000,
        deletedDentistErrorModal: 4000,
        integrityEmailErrorModal: 4000
    };

    Object.entries(modaisComTempo).forEach(([id, tempo]) => closeModalById(id, tempo));
};

function closeModalById(id, delay = 4000) {
    const modal = document.getElementById(id);
    if (modal) {
        setTimeout(() => {
            modal.style.display = "none";
        }, delay);
    }
}

function closeModalByElement(element) {
    const modal = element.closest(".modal");
    if (modal) modal.style.display = "none";
}
