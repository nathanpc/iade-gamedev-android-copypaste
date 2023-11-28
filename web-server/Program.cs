int counter = 0;
string the_string = "Some string";

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

app.UseStaticFiles();
app.UseRouting();
app.UseAuthorization();

app.MapGet("/secret_of_life", () =>
{
    return "42";
});

app.MapGet("/count", () =>
{
    counter++;
    return counter.ToString();
});

app.MapPost("set_string", (string some_string) =>
{
    the_string = some_string;
});

app.MapGet("/get_string", () =>
{
    return the_string;
});

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
    app.UseSwaggerUI(options =>
    {
        options.SwaggerEndpoint("/swagger/v1/swagger.json", "v1");
        options.RoutePrefix = string.Empty;
    });
}

app.Run();

