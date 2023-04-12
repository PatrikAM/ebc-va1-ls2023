package cz.mendelu.pef.va1.xmichl.homework2.navigation

sealed class Destination(val route: String) {
    object ContactListScreen : Destination(route = "contact_list")
    object NewContactScreen : Destination(route = "new_contact")
}
