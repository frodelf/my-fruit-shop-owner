var page = 0
getPageWithFilter(page)

$(document).ready(function () {
    let timeout = null
    $("#product-search").on('input', function () {
        clearTimeout(timeout)
        timeout = setTimeout(() => {
            getPageWithFilter(0)
        }, 500)
    })
})

function getPageWithFilter(page) {
    showLoader('product-table')
    var query = $("#product-search").val() || ''
    this.page = page
    $.ajax({
        type: "Get",
        url: contextPath + 'product/get-all',
        data: {
            page: page,
            pageSize: pageSize,
            query: query
        },
        success: function (objects) {
            var table = document.getElementById("product-table");
            var tbody = table.querySelector("tbody");
            $('#product-table tbody').empty();
            if ($("#message-about-empty")) $("#message-about-empty").remove()
            if (objects.length == 0) {
                table.insertAdjacentHTML('afterend', '<center><h1 id="message-about-empty">Немає даних для відображення</h1></center>')
                $('#pagination_container').empty()
                return
            }
            for (var object of objects) {
                var newRow = tbody.insertRow();
                var cell0 = newRow.insertCell(0);
                cell0.innerHTML = `${object.name}`;

                var cell1 = newRow.insertCell(1);
                cell1.innerHTML = `${object.number}`;

                var cell2 = newRow.insertCell(2);
                cell2.innerHTML = `courses`;

                var cell3 = newRow.insertCell(3);
                cell3.innerHTML = Object.entries(object.owner).map(([key, value]) => {
                    return `<a href="javascript:void(0)">${value}</a>`
                })
                var cell4 = newRow.insertCell(4);
                cell4.innerHTML = `${object.lastOperation}`;
            }
            createPaginationBlock(page, query)
        },
        complete: function (xhr, status) {
            hideLoader("product-table")
        }
    })
}

function createPaginationBlock(page, query) {
    showLoader('pagination_container')
    $.ajax({
        type: "Get",
        url: contextPath + 'product/get-total-for-pagination',
        data: {
            pageSize: pageSize,
            name: query,
        },
        success: function (total) {
            $('#pagination_container').empty();
            if (total > 1) updatePagination(page, total, 'pagination_container')
        },
        complete: function (xhr, status) {
            hideLoader("pagination_container")
        }
    })
}