var messageForDelete = "Об'єкт успішно видалено"
var messageForSave = "Об'єкт успішно збережено"

function showLoader(blockId) {
    $("#" + blockId).block({
        message: `
            <div class="d-flex justify-content-center">
                <p class="me-2 mb-0">Please wait...</p>
                <div class="sk-wave sk-primary m-0">
                    <div class="sk-rect sk-wave-rect"></div>
                    <div class="sk-rect sk-wave-rect"></div>
                    <div class="sk-rect sk-wave-rect"></div>
                    <div class="sk-rect sk-wave-rect"></div>
                    <div class="sk-rect sk-wave-rect"></div>
                </div>
            </div>`,
        css: {
            backgroundColor: "transparent",
            border: "0"
        },
        overlayCSS: {
            backgroundColor: "#fff",
            opacity: 0.8
        }
    });
}

function hideLoader(blockId) {
    $("#" + blockId).unblock();
}

function showSuccessToast(message) {
    toastr.options.closeButton = true
    toastr.options.progressBar = true;
    toastr.success(message)
}

function showErrorToast(message) {
    toastr.options.closeButton = true
    toastr.options.progressBar = true;
    toastr.error(message)
}

function showToastForDelete() {
    toastr.options.closeButton = true
    toastr.options.progressBar = true;
    toastr.success(messageForDelete)
}

function showToastForSave() {
    toastr.options.closeButton = true
    toastr.options.progressBar = true;
    toastr.success(messageForSave)
}

var countError = 0;

function validDataFromResponse(errors) {
    cleanInputs()
    for (var fieldName in errors) {
        if (errors.hasOwnProperty(fieldName)) {
            var errorMessage = errors[fieldName];
            scrollToElement($('#' + fieldName.toString()));
            addText($('#' + fieldName.toString()), errorMessage)
            $('#' + fieldName.toString()).css("border", "1px solid #ff0000")
        }
    }
    countError = 0
}
function addText(input, message) {
    message = translateError(message)
    var icon = $('<p class="text-for-validating" style="color: #ff0000;">' + message + '</p>')
    icon.tooltip({
        content: message,
        position: {my: "left+15 center", at: "right center"}
    })
    if (input.is('select')) {
        input.next().find(".select2-selection").css("border", "1px solid #ff0000")
        if(message == 'Please fill in the field!')addText(input.next().find(".select2-selection"), "Елемент має бути вибрано")
        addText(input.next().find(".select2-selection"), message)
        return
    }
    input.after(icon);
    input.css("border-color", "#ff0000")
}

function modalForLogoutModal() {
    var modalBlock = document.createElement('div');
    modalBlock.innerHTML = `
        <div class="modal fade" id="ModalForLogout" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header text-center" style="font-size: 20px">
                        Ви впевнені що хочете вийти?
                    </div>
                    <div class="modal-footer">
                        <a href="${contextPath}logout" class="btn btn-danger float-end">Вийти</a>
                        <button class="btn btn-secondary float-end" data-bs-dismiss="modal">Скасувати</button>
                    </div>
                </div>
            </div>
        </div>
    `;
    document.body.appendChild(modalBlock);
    $('#ModalForLogout').modal('show');
}

function translateError(key) {
    return key
        .replace('Please fill in the field', 'Заповніть поле')
        .replace('The number should be greater than', 'Число повинно бути більше, ніж')
        .replace('The number should be less than', 'Число повинно бути менше, ніж')
        .replace('The field should have fewer than', 'Поле повинно містити менше ніж')
        .replace('characters and more than', 'і більше ніж')
        .replace('ones', 'символів')
        .replace('characters', 'символів')
        .replace('Invalid email format', 'Невірний формат електронної пошти')
        .replace('Invalid phone format', 'Невірний формат телефону')
        .replace('File extension not valid', 'Тип файлу повиннен бути .jpeg, .png, .jpg')
        .replace('Invalid url format!', 'Значення має бути посиланням')
        .replace('The phone already exists!', 'Такий телефон вже існує')
        .replace('The telegram already exists!', 'Такий телеграм вже існує')
        .replace('The email already exists!', 'Такий електроний адрес вже існує')
}
function translateTest(text) {
    if (text == "CLOSE") text = "Закрите"
    if (text == "OPEN") text = "Відкрите"
    if (text == "IN_PROGRESS") text = "В процесі"
    if (text == "FINISHED") text = "Завершено"
    return text
}
function modalForRemoveObject(objectId, urlForRemove) {
    if ($('#modalForRemove').html()) $('#modalForRemove').remove()

    var modalBlock = document.createElement('div');
    modalBlock.innerHTML = `
        <div class="modal fade" id="modalForRemove" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Ви впевені що хочете видалити об'єкт</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-footer">
                        <button class="float-end btn btn-danger" onclick="removeObject(${objectId}, '${urlForRemove}')">Видалити</button>
                    </div>
                </div>
            </div>
        </div>
    `
    document.body.appendChild(modalBlock)
    $('#modalForRemove').modal('show')
}