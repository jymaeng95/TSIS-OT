<template>
    <div>
        {{this.seat}}

        {{this.movieNo}}
    </div>
</template>

<script>
export default {
    name : 'ticketing',
    props: {
        seat : {
            type :String
        },
        movieNo : {
            type : String
        }
        
    },
}
</script>

<style>

</style>