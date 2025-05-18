document.addEventListener("DOMContentLoaded", function () {
    const urlParams = new URLSearchParams(window.location.search);
    const erro = urlParams.get("erro");

    if (erro) {
        alert(erro);
    }
});

function closeStatusModal() {
    const modal = document.getElementById('statusUpdatedModal');
    if (modal) modal.style.display = 'none';
}

window.onload = function () {
    setTimeout(closeStatusModal, 4000);
};

function closeDeletedModal() {
    const modal = document.getElementById('deletedDentistModal');
    if (modal) modal.style.display = 'none';
}

function closeEditedModal() {
    const modal = document.getElementById('editedDentistModal');
    if (modal) modal.style.display = 'none';
}

function closeDentistModal() {
    const modal = document.getElementById('emptyDentistModal');
    if (modal) modal.style.display = 'none';
}

function closeModal() {
    document.getElementById('logoutModal').style.display = 'none';
}

function closeErrorModal() {
    document.getElementById('errorModal').style.display = 'none';
}

window.onload = function () {
    setTimeout(closeDeletedModal, 4000);
    setTimeout(closeEditedModal, 4000);
    setTimeout(closeDentistModal, 4000);
};
window.onload = function () {
    setTimeout(closeModal, 3000);
    setTimeout(closeErrorModal, 3000);
};


window.onload = function () {
    setTimeout(closeResumoModal, 8000);
    setTimeout(closeCreatedFeedbackModal, 4000);
    setTimeout(closeDeletedFeedbackModal, 4000);
    setTimeout(closeEmptyFeedbackModal, 4000);
    setTimeout(closeDeletedFeedbackErrorModal, 4000);
};

function closeDeletedFeedbackErrorModal() {
    document.getElementById('deletedFeedbackErrorModal').style.display = 'none';
}
function closeCreatedFeedbackModal() {
    document.getElementById('createdFeedbackModal').style.display = 'none';
}
function closeDeletedFeedbackModal() {
    document.getElementById('deletedFeedbackModal').style.display = 'none';
}
function closeEmptyFeedbackModal() {
    document.getElementById('emptyFeedbackModal').style.display = 'none';
}
function closeResumoModal() {
    document.getElementById('resumoModal').style.display = 'none';
}

function closeUpdatedClinicaModal() {
    document.getElementById('updatedClinicaModal').style.display = 'none';
}

window.onload = function () {
    setTimeout(closeUpdatedClinicaModal, 4000);
};

function closeEditedFeedbackModal() {
    document.getElementById('editedFeedbackModal').style.display = 'none';
}

window.onload = function () {
    setTimeout(closeResumoModal, 8000);
    setTimeout(closeCreatedFeedbackModal, 4000);
    setTimeout(closeDeletedFeedbackModal, 4000);
    setTimeout(closeEmptyFeedbackModal, 4000);
    setTimeout(closeDeletedFeedbackErrorModal, 4000);
    setTimeout(closeEditedFeedbackModal, 4000);
};
