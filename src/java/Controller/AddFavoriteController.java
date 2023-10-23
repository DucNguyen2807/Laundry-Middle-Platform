package Controller;

import Model.User;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddFavoriteController", urlPatterns = {"/AddFavoriteController"})
public class AddFavoriteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");

        
        if (user != null) {
            int userID = user.getUserId();
            String storeID = request.getParameter("storeID");
            
            if (storeID != null) {
                UserService usersv = new UserService();
                try {
                    boolean success = usersv.addToFavorites(userID, storeID);
                    if (success) {
                        response.getWriter().println("Cửa hàng với ID " + storeID + " đã được thêm vào danh sách yêu thích.");
                    } else {
                        response.getWriter().println("Có lỗi xảy ra khi thêm cửa hàng vào danh sách yêu thích.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Có lỗi xảy ra khi thêm cửa hàng vào danh sách yêu thích.");
                }
            } else {
                response.getWriter().println("Lỗi: Không có ID cửa hàng được cung cấp.");
            }
        } else {
            response.getWriter().println("Lỗi: Người dùng chưa đăng nhập hoặc phiên làm việc đã hết hạn.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Add to Favorites Controller";
    }
}
