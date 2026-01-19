process.on('unhandledRejection', (err:any) => {
    console.log(err.prototype)
    console.log('UNHANDLER REJECTION');
    process.exit(1); 

})

process.on('uncaughtException', (err:any) => {
    console.log(err)
    console.log(err.prototype)
    console.log('UNHANDLER EXCEPTION');
    process.exit(1); 
})