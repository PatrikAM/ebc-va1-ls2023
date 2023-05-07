package cz.mendelu.pef.va1.xmichl.meminiapp.extensions

fun Double.round(): String {
    return String.format("%.2f", this)
}